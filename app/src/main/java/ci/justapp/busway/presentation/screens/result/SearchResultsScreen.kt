package ci.justapp.busway.presentation.screens.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ci.justapp.busway.presentation.components.PlaceCard
import ci.justapp.busway.presentation.components.SearchBar

@Composable
fun SearchResultsScreen(
    searchQuery: String,
    onBackClick: () -> Unit,
    onResultClick: (String) -> Unit,
    onNewSearch: (String) -> Unit = {}
) {
    var currentQuery by remember { mutableStateOf(searchQuery) }
    var isSearching by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // En-tête avec indication visuelle de la section active
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = "Search Results",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "for \"$searchQuery\"",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Barre de recherche interactive
        SearchBar(
            value = currentQuery,
            onValueChange = {
                currentQuery = it
                isSearching = true
            },
            placeholder = "Search destination",
            readOnly = false,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Bouton de recherche qui apparaît lorsque l'utilisateur modifie la requête
        if (isSearching) {
            Button(
                onClick = {
                    isSearching = false
                    onNewSearch(currentQuery)  // Appel de la fonction pour effectuer une nouvelle recherche
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Search")
            }
        }

        // Message indiquant le nombre de résultats
        Text(
            text = "3 results found for \"$searchQuery\"",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Liste des résultats
        LazyColumn {
            item {
                PlaceCard(
                    name = "Stasiun Gambir",
                    address = "Jl. Medan Merdeka Tim. No.1, Gambir, Kecamatan Gambir, Kota Jakarta Pusat",
                    onPlaceClick = { onResultClick("Stasiun Gambir") }
                )
            }

            item {
                PlaceCard(
                    name = "Monumen Nasional",
                    address = "RT.5/RW.2, Gambir, Central Jakarta City, Jakarta 10110",
                    onPlaceClick = { onResultClick("Monumen Nasional") }
                )
            }

            item {
                PlaceCard(
                    name = "Central Park Mall",
                    address = "Letjen S. Parman St No.Kavling 28, North Tanjung Duren, Grogol petamburan",
                    onPlaceClick = { onResultClick("Central Park Mall") }
                )
            }
        }
    }
}