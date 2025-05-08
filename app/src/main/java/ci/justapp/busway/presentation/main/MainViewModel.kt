package ci.justapp.busway.presentation.main

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

    fun fetchCurrentLocation() {
        // Ajoute ici la logique pour récupérer la vraie position (si nécessaire)
    }
}
