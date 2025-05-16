package ci.justapp.busway.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ci.justapp.busway.presentation.components.LocationMap
import ci.justapp.busway.presentation.components.MapContainer
import ci.justapp.busway.presentation.components.PreviousSearchSection
import ci.justapp.busway.presentation.components.SavedChip
import ci.justapp.busway.presentation.components.SearchBar

@Composable
fun MainScreen(
    onSearchRequested: (prefill: String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    // Forcer l'initialisation des données au premier affichage
    LaunchedEffect(Unit) {
        viewModel.fetchCurrentLocation()
    }

    val loc by viewModel.locationUiState.collectAsState()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        /* 1. Carte */
        item {
            MapContainer {
                LocationMap(
                    latitude = loc.latitude,
                    longitude = loc.longitude,
                    modifier = Modifier
                        .height(screenHeight * .30f)
                        .fillMaxWidth()
                )
            }
        }

        /* 2. SearchBar */
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Search for a destination", style = MaterialTheme.typography.titleMedium)
                // Toujours naviguer vers l'écran de recherche avec un préfixe vide
                SearchBar(
                    placeholder = "Select location",
                    onClick = { onSearchRequested("") },  // Toujours appeler avec une chaîne vide
                    readOnly = true  // Garder en lecture seule pour forcer la navigation
                )
            }
        }

        /* 3. Saved chips */
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Saved location", style = MaterialTheme.typography.titleMedium)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Naviguer vers l'écran de recherche avec les valeurs préremplies
                    SavedChip(Icons.Filled.Home, "Our Home") { onSearchRequested("Our Home") }
                    SavedChip(
                        Icons.Filled.AccountBox,
                        "My Office"
                    ) { onSearchRequested("My Office") }
                    SavedChip(Icons.Filled.Place, "Danilla") { onSearchRequested("Danilla") }
                }
            }
        }

        /* 4. Previous searches */
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Your previous searches", style = MaterialTheme.typography.titleMedium)
                // Naviguer vers l'écran de recherche avec la destination sélectionnée
                PreviousSearchSection { dest -> onSearchRequested(dest) }
            }
        }
    }
}