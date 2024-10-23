package com.example.nit3213assessment2.ui

import com.example.nit3213assessment2.data.LoginReceive
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.nit3213assessment2.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModels()

    private var nav: NavController?= null

    fun checkDetails(usernameInput:String,
                     passwordInput:String,
                     correctUsername:String,
                     correctPassword:String) : Boolean {
        return !(usernameInput != correctUsername || passwordInput != correctPassword)
    }

    fun loginError(usernameInput:String,
                   passwordInput:String,
                   correctUsername:String,
                   correctPassword:String) : String {
        return when { // This is probably a messy way of doing things, but I wasn't sure how else I could elegantly do it.
            usernameInput != correctUsername && passwordInput != correctPassword -> "Both fields invalid"
            passwordInput != correctPassword -> "Invalid password"
            usernameInput != correctUsername -> "Invalid username"
            else -> "What have you done..."
        }
    }

    @Inject
    @Named("Username")
    lateinit var usernameCorrect:String

    @Inject
    @Named("Password")
    lateinit var passwordCorrect: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nav = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.loginButton)?.setOnClickListener(this)

        viewModel.getKeyPass()
    }

    override fun onClick(v: View?) {
        val usernameInput = view?.findViewById<TextView>(R.id.usernameField)?.getText().toString()
        val passwordInput = view?.findViewById<TextView>(R.id.passwordField)?.getText().toString()

        if (checkDetails(usernameInput, passwordInput, usernameCorrect, passwordCorrect)) {
            Log.v("NIT3213", "$usernameInput has logged in.")
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.objectsState.collect { itemsInApiResponse ->
                        if (itemsInApiResponse == LoginReceive(keypass = "books")) { //If I want to expand this in the future, I'll have to run the output of the post when the user logs in so they can access their stuff even if they don't have books as their thingo.
                            nav?.navigate(R.id.action_loginFragment_to_dashboardFragment)
                        } else {
                            Log.v("NIT3213","Mismatch username and endpoint!")
                            view?.findViewById<TextView>(R.id.loginErrorTextView)?.text = "Mismatch in backend, please fix!" //This would be weird.
                        }
                    }
                }
            }


        } else {
            val loginErrorMessage = loginError(usernameInput, passwordInput, usernameCorrect, passwordCorrect)
            view?.findViewById<TextView>(R.id.loginErrorTextView)?.text = loginErrorMessage

        }
    }
}