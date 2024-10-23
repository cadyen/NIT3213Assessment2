package com.example.nit3213assessment2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nit3213assessment2.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(), View.OnClickListener {
    private val viewModel: DetailsViewModel by viewModels() // ViewModel for data handling
    private var navController: NavController? = null // Navigation controller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("NIT3213", "DetailsFragment Created")

        // Initialize the navigation controller
        navController = Navigation.findNavController(view)

        // Set up back button click listener
        view.findViewById<ImageButton>(R.id.backButton)?.setOnClickListener(this)

        // Retrieve the index of the selected item from the arguments
        val selectedItemIndex = arguments?.getInt("SelectedItemIndex")

        // Fetch all objects from the ViewModel
        viewModel.getAllObjects()

        // Collect and display data when the lifecycle is in the STARTED state
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.entitiesState.collect { itemsInApiResponse ->
                    if (itemsInApiResponse.isNotEmpty() && selectedItemIndex != null) {
                        // Display the details of the selected item
                        val selectedItem = itemsInApiResponse[selectedItemIndex]
                        view.findViewById<TextView>(R.id.detailsHeading).text = selectedItem.title
                        view.findViewById<TextView>(R.id.titleTextView).text = selectedItem.title
                        view.findViewById<TextView>(R.id.authorTextView).text = selectedItem.author
                        view.findViewById<TextView>(R.id.genreTextView).text = selectedItem.genre
                        view.findViewById<TextView>(R.id.publishedYearTextView).text = selectedItem.publicationYear
                        view.findViewById<TextView>(R.id.descriptionTextView).text = selectedItem.description
                    }
                }
            }
        }
    }

    // Navigate back to the dashboard fragment when the back button is pressed
    override fun onClick(v: View?) {
        Log.v("NIT3213", "Back button pressed")
        navController?.navigate(R.id.action_detailsFragment_to_dashboardFragment)
    }
}
