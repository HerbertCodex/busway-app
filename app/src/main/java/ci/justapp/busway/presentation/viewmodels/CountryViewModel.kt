package ci.justapp.busway.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.models.CountryModel
import ci.justapp.busway.domain.repositories.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val countries: List<CountryModel>, val selectedCountry: CountryModel? = null) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadCountries() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                countryRepository.getCountries()
                    .collect { countries -> _uiState.value = UiState.Success(countries) }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load countries")
            }
        }
    }

    fun loadCountryBySlug(slug: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val country = countryRepository.getCountryBySlug(slug)
                val currentCountries = (uiState.value as? UiState.Success)?.countries ?: emptyList()
                if (country != null) {
                    _uiState.value = UiState.Success(currentCountries, country)
                } else {
                    _uiState.value = UiState.Error("Country not found")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load country")
            }
        }
    }

    /** Synchronise les pays depuis l'API distante et recharge l'état */
    fun syncCountriesFromApi() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                countryRepository.fetchAndStoreCountriesFromApi() // récupère et insère
                loadCountries() // recharge depuis Room
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to sync countries")
            }
        }
    }
}