package ci.justapp.busway.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.models.DataMetadataModel
import ci.justapp.busway.domain.repositories.DataMetadataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataMetadataViewModel @Inject constructor(
    private val dataMetadataRepository: DataMetadataRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(
            val metadataList: List<DataMetadataModel>,
            val selectedMetadata: DataMetadataModel? = null
        ) : UiState()

        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadMetadata() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                dataMetadataRepository.getMetadata().collect { metadataList ->
                    _uiState.value = UiState.Success(metadataList)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load metadata")
            }
        }
    }

    fun loadMetadataById(id: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val metadata = dataMetadataRepository.getMetadataById(id)
                val currentList = (uiState.value as? UiState.Success)?.metadataList ?: emptyList()
                if (metadata != null) {
                    _uiState.value = UiState.Success(currentList, metadata)
                } else {
                    _uiState.value = UiState.Error("Metadata not found")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load metadata by id")
            }
        }
    }

    /** Synchronise les métadonnées depuis l’API distante et recharge la liste */
    fun syncMetadataFromApi() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                dataMetadataRepository.fetchAndStoreDataMetadataFromApi()
                loadMetadata()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to sync metadata")
            }
        }
    }
}