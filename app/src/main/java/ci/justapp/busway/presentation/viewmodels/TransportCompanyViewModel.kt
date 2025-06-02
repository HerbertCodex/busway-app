package ci.justapp.busway.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.models.TransportCompanyModel
import ci.justapp.busway.domain.repositories.TransportCompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransportCompanyViewModel @Inject constructor(
    private val transportCompanyRepository: TransportCompanyRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(
            val companies: List<TransportCompanyModel>,
            val selectedCompany: TransportCompanyModel? = null
        ) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadCompanies() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                transportCompanyRepository.getCompanies()
                    .collect { companies ->
                        _uiState.value = UiState.Success(companies)
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Erreur lors du chargement")
            }
        }
    }

    fun loadCompanyBySlug(slug: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val company = transportCompanyRepository.getCompanyBySlug(slug)
                val currentList = (uiState.value as? UiState.Success)?.companies ?: emptyList()
                if (company != null) {
                    _uiState.value = UiState.Success(currentList, company)
                } else {
                    _uiState.value = UiState.Error("Compagnie introuvable")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Erreur de récupération")
            }
        }
    }

    fun syncCompaniesFromApi() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                transportCompanyRepository.fetchAndStoreTransportCompaniesFromApi()
                loadCompanies()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Erreur de synchronisation")
            }
        }
    }
}
