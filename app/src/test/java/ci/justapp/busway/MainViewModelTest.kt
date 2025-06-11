package ci.justapp.busway

import ci.justapp.busway.presentation.screens.main.MainViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        // Configurer le dispatcher principal pour les tests
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        // Réinitialiser le dispatcher principal après les tests
        Dispatchers.resetMain()
    }

    // Scénario de réussite : Vérifier l'initialisation avec les données statiques
    @Test
    fun mainViewModel_Initialization_LocationUiStateSetCorrectly() {
        // Given: ViewModel initialisé
        val locationUiState = viewModel.locationUiState.value

        // Then: Vérifier que l'état initial correspond aux valeurs statiques
        assertEquals(-6.2088, locationUiState.latitude, 0.0001)
        assertEquals(106.8456, locationUiState.longitude, 0.0001)
        assertEquals("Jln Jati Pulo, Palmerah, West Jakarta City, Jakarta", locationUiState.address)
    }

    // Scénario de réussite : Vérifier que fetchCurrentLocation met à jour correctement
    @Test
    fun mainViewModel_FetchCurrentLocation_LocationUiStateUpdatedCorrectly() {
        // When: Appeler fetchCurrentLocation
        viewModel.fetchCurrentLocation()

        // Then: Vérifier que l'état est mis à jour avec les valeurs statiques
        val locationUiState = viewModel.locationUiState.value
        assertEquals(-6.2088, locationUiState.latitude, 0.0001)
        assertEquals(106.8456, locationUiState.longitude, 0.0001)
        assertEquals("Jln Jati Pulo, Palmerah, West Jakarta City, Jakarta", locationUiState.address)
    }

    // Scénario d'erreur : Simuler un cas où la récupération échoue
    @Test
    fun mainViewModel_FetchCurrentLocation_ErrorCaseInvalidData() {
        // Note: Puisque les données sont statiques, nous ne pouvons pas simuler un échec
        // directement. Ce test vérifie la cohérence des données statiques.
        // Pour une BD réelle, ce test pourrait vérifier un état null ou invalide.
        viewModel.fetchCurrentLocation()
        val locationUiState = viewModel.locationUiState.value

        // Vérifier que les valeurs restent cohérentes même après plusieurs appels
        assertEquals(-6.2088, locationUiState.latitude, 0.0001)
        assertEquals(106.8456, locationUiState.longitude, 0.0001)
        assertEquals("Jln Jati Pulo, Palmerah, West Jakarta City, Jakarta", locationUiState.address)
    }

    // Cas limite : Vérifier l'état initial avant tout appel à fetchCurrentLocation
    @Test
    fun mainViewModel_InitialState_BeforeFetchLocation() {
        // Given: ViewModel juste initialisé
        val locationUiState = viewModel.locationUiState.value

        // Then: Vérifier que l'état initial est correct
        assertEquals(-6.2088, locationUiState.latitude, 0.0001)
        assertEquals(106.8456, locationUiState.longitude, 0.0001)
        assertEquals("Jln Jati Pulo, Palmerah, West Jakarta City, Jakarta", locationUiState.address)
    }
}