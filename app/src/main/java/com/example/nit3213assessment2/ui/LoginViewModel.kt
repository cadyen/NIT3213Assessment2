package com.example.nit3213assessment2.ui

import com.example.nit3213assessment2.data.LoginReceive
import com.example.nit3213assessment2.data.LoginSend
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213assessment2.data.ApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository : ApiRepo) : ViewModel()  {

    private val mutableObjectsState = MutableStateFlow(LoginReceive(keypass = ""))
    val objectsState: StateFlow<LoginReceive> = mutableObjectsState

    private val _errorState = MutableStateFlow<String?>(null)

    fun getKeyPass() {
        viewModelScope.launch {
            try {
                val entities = repository.getKeyPass(LoginSend("s8005276", "Cayden"))
                mutableObjectsState.value = entities
            } catch (e: Exception) {
                _errorState.value = "${e.message}"
            }
        }
    }
}