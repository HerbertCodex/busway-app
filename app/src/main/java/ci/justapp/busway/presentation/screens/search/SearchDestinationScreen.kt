import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import ci.justapp.busway.presentation.components.PreviousSearchSection
import ci.justapp.busway.presentation.components.SavedLocationsRow
import ci.justapp.busway.presentation.components.SearchBar

@Composable
fun SearchDestinationScreen(
    initialQuery: String,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLocationSelect: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf(initialQuery) }

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

            Text(
                text = "Search",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Barre de recherche interactive
        SearchBar(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = "Search destination",
            readOnly = false,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Bouton de recherche
        if (searchQuery.isNotEmpty()) {
            Button(
                onClick = onSearchClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Search")
            }
        }

        // Section des emplacements enregistrés
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Saved locations",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            SavedLocationsRow(
                onHomeClick = { onLocationSelect("Home") },
                onOfficeClick = { onLocationSelect("Office") }
            )
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Section des recherches récentes
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Recent searches",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            PreviousSearchSection { destination ->
                onLocationSelect(destination)
            }
        }
    }
}
