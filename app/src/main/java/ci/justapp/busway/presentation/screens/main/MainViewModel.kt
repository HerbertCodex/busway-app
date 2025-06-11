package ci.justapp.busway.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.justapp.busway.domain.repositories.DataRepository
import ci.justapp.busway.presentation.models.LocationUiState
import ci.justapp.busway.presentation.viewmodels.CityViewModel.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val _locationUiState = MutableStateFlow(
        LocationUiState(
            latitude = -6.2088,
            longitude = 106.8456,
            address = "Jln Jati Pulo, Palmerah, West Jakarta City, Jakarta"
        )
    )
    val locationUiState: StateFlow<LocationUiState> = _locationUiState.asStateFlow()

    // Initialiser les données au démarrage du ViewModel
    init {
        fetchCurrentLocation()
    }

    fun fetchCurrentLocation() {
        // Pour l'instant, on utilise des données statiques
        // Dans une implémentation réelle, vous récupéreriez la position de l'utilisateur ici
        _locationUiState.value = LocationUiState(
            latitude = -6.2088,
            longitude = 106.8456,
            address = "Jln Jati Pulo, Palmerah, West Jakarta City, Jakarta"
        )
    }

    fun deleteAllData(){
        viewModelScope.launch {
            try {
                dataRepository.deleteAll()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
