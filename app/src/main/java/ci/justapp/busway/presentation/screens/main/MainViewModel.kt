package ci.justapp.busway.presentation.screens.main

import androidx.lifecycle.ViewModel
import ci.justapp.busway.presentation.models.LocationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

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
}
