package ci.justapp.busway.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.models.CityModel
import ci.justapp.busway.domain.repositories.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {
    sealed class UiState {
        object Loading : UiState()
        data class Success(val cities: List<CityModel>, val selectedCity: CityModel? = null) :
            UiState()

        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadCities() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                cityRepository.getCities()
                    .collect { cities -> _uiState.value = UiState.Success(cities) }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load cities")
            }
        }
    }

    fun loadCityBySlug(slug: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val city = cityRepository.getCityBySlug(slug)
                val currentCities = (uiState.value as? UiState.Success)?.cities ?: emptyList()
                if (city != null) {
                    _uiState.value = UiState.Success(currentCities, city)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load city")
            }
        }
    }
}