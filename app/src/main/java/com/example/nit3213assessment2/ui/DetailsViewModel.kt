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
class DetailsViewModel @Inject constructor(private val repository: ApiRepo) : ViewModel() {

    // StateFlow for holding the list of entities
    private val _entitiesState = MutableStateFlow<List<Entity>>(emptyList())
    val entitiesState: StateFlow<List<Entity>> = _entitiesState

    // StateFlow for holding the total number of entities
    private val _entityTotalState = MutableStateFlow(0)

    // StateFlow for holding error messages
    private val _errorState = MutableStateFlow<String?>(null)

    // Function to fetch all entities from the repository
    fun getAllObjects() {
        viewModelScope.launch {
            try {
                val response = repository.getAllEntities()
                _entitiesState.value = response.entities // Update entities state
                _entityTotalState.value = response.entityTotal // Update total entities
            } catch (e: Exception) {
                _errorState.value = "Error fetching entities: ${e.message}" // Handle error
            }
        }
    }
}
