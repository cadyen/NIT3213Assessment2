package com.example.nit3213assessment2.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213assessment2.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: MyAdaptor
    private lateinit var clickLambdaFunction: () -> Unit

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllObjects()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.entitiesState.collect { itemsInApiResponse ->
                    if (itemsInApiResponse.isNotEmpty()) {

                        clickLambdaFunction= { Log.v("NIT3213","Item Clicked")}

                        val itemList = mutableListOf<String>()
                        itemsInApiResponse.map { entity ->
                            itemList += (""""${entity.title}"""" + " by ${entity.author}.\n" +
                                    "${entity.genre}, ${entity.publicationYear}")
                        }

                        recyclerView = view.findViewById(R.id.recyclerView)
                        adaptor = MyAdaptor(itemList,clickLambdaFunction)
                        recyclerView.adapter=adaptor
                        adaptor.updateData(itemList)
                    }
                }
            }
        }
    }
}