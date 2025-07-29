package com.pawgram.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pawgram.app.data.model.Pet
import com.pawgram.app.data.model.PetCareCenter
import com.pawgram.app.data.model.User
import com.pawgram.app.data.repository.PawgramRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PawgramRepository) : ViewModel() {
    
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
//            _uiState.value = _uiState.value.copy(isLoading = true)
            
            // Load user data
            repository.getCurrentUser().collect { user ->
                _uiState.value = HomeUiState.Loaded(
                    user = user
                )
            }
        }
        
        viewModelScope.launch {
            // Load pets data
            repository.getPets().collect { pets ->
                (_uiState.value as? HomeUiState.Loaded)?.let {
                    _uiState.value = (it).copy(pets = pets)
                }
            }
        }
        
        viewModelScope.launch {
            // Load pet care centers data
            repository.getPetCareCenters().collect { centers ->
                (_uiState.value as? HomeUiState.Loaded)?.let {
                    _uiState.value = (it).copy(petCareCenters = centers)
                }
            }
        }
    }
    
    fun refreshData() {
        loadData()
    }
}


sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Loaded(
        val user: User? = null,
        val pets: List<Pet> = emptyList(),
        val petCareCenters: List<PetCareCenter> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    ) : HomeUiState
    data class Error(val message: String): HomeUiState
}