package ci.justapp.busway.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.models.TransportModeModel
import ci.justapp.busway.domain.repositories.TransportModeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransportModeViewModel @Inject constructor(
    private val repository: TransportModeRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val modes: List<TransportModeModel>, val selected: TransportModeModel? = null) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadTransportModes() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                repository.getTransportModes()
                    .collect { modes ->
                        _uiState.value = UiState.Success(modes)
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load transport modes")
            }
        }
    }

    fun loadTransportModeBySlug(slug: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val selected = repository.getTransportModeBySlug(slug)
                val current = (uiState.value as? UiState.Success)?.modes ?: emptyList()
                if (selected != null) {
                    _uiState.value = UiState.Success(current, selected)
                } else {
                    _uiState.value = UiState.Error("Transport mode not found")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load transport mode")
            }
        }
    }

    fun syncTransportModesFromApi() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                repository.fetchAndStoreTransportModesFromApi()
                loadTransportModes()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to sync transport modes")
            }
        }
    }
}
