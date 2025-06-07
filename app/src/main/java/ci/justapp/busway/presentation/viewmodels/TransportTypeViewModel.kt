package ci.justapp.busway.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.models.TransportTypeModel
import ci.justapp.busway.domain.repositories.TransportTypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransportTypeViewModel @Inject constructor(
    private val repository: TransportTypeRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(
            val types: List<TransportTypeModel>,
            val selected: TransportTypeModel? = null
        ) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadTransportTypes() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                repository.getTransportTypes()
                    .collect { types ->
                        _uiState.value = UiState.Success(types)
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load transport types")
            }
        }
    }

    fun loadTransportTypeBySlug(slug: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val selected = repository.getTransportTypeBySlug(slug)
                val current = (uiState.value as? UiState.Success)?.types ?: emptyList()
                if (selected != null) {
                    _uiState.value = UiState.Success(current, selected)
                } else {
                    _uiState.value = UiState.Error("Transport type not found")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load transport type")
            }
        }
    }

    fun syncTransportTypesFromApi() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                repository.fetchAndStoreTransportTypesFromApi()
                loadTransportTypes()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to sync transport types")
            }
        }
    }
}
