package ci.justapp.busway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.models.CommuneModel
import ci.justapp.busway.domain.repositories.CommuneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommuneViewModel @Inject constructor(
    private val communeRepository: CommuneRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val communes: List<CommuneModel>, val selectedCommune: CommuneModel? = null) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadCommunes() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                communeRepository.getCommunes()
                    .collect { communes -> _uiState.value = UiState.Success(communes) }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Échec du chargement des communes")
            }
        }
    }

    fun loadCommuneBySlug(slug: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val commune = communeRepository.getCommuneBySlug(slug)
                val current = (uiState.value as? UiState.Success)?.communes ?: emptyList()
                if (commune != null) {
                    _uiState.value = UiState.Success(current, commune)
                } else {
                    _uiState.value = UiState.Error("Commune introuvable")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Échec du chargement de la commune")
            }
        }
    }

    fun syncCommunesFromApi() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                communeRepository.fetchAndStoreCommunesFromApi()
                loadCommunes()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Échec de la synchronisation")
            }
        }
    }
}
