package com.example.nit3213assessment2.ui

import com.example.nit3213assessment2.data.Entity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213assessment2.data.ApiRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: ApiRepo) : ViewModel() { // Injecting the repository
    private val _entitiesState = MutableStateFlow<List<Entity>>(emptyList())

    val entitiesState: StateFlow<List<Entity>> = _entitiesState

    private val entityTotalState = MutableStateFlow(0)
    private val errorState = MutableStateFlow<String?>(null)

    fun getAllObjects() {
        viewModelScope.launch {
            try {
                val response = repository.getAllEntities()
                _entitiesState.value = response.entities
                entityTotalState.value = response.entityTotal
            } catch (e: Exception) {
                errorState.value = "Error fetching objects: ${e.message}"
            }
        }
    }
}
