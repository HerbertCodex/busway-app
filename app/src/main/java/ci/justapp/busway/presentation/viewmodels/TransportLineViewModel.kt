package ci.justapp.busway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.models.TransportLineModel
import ci.justapp.busway.domain.repositories.TransportLineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransportLineViewModel @Inject constructor(
    private val repository: TransportLineRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(
            val lines: List<TransportLineModel>,
            val selected: TransportLineModel? = null
        ) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadTransportLines() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                repository.getTransportLines()
                    .collect { lines ->
                        _uiState.value = UiState.Success(lines)
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load transport lines")
            }
        }
    }

    fun loadTransportLineBySlug(slug: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val selected = repository.getTransportLineBySlug(slug)
                val current = (uiState.value as? UiState.Success)?.lines ?: emptyList()
                if (selected != null) {
                    _uiState.value = UiState.Success(current, selected)
                } else {
                    _uiState.value = UiState.Error("Transport line not found")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load transport line")
            }
        }
    }

    fun syncTransportLinesFromApi() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                repository.fetchAndStoreTransportLinesFromApi()
                loadTransportLines()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to sync transport lines")
            }
        }
    }
}
