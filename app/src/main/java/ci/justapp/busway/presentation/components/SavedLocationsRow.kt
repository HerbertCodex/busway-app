package ci.justapp.busway.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun SavedLocationsRow(
    onHomeClick: () -> Unit,
    onOfficeClick: () -> Unit
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SavedChip(Icons.Outlined.Home, "Our Home", onHomeClick)
        SavedChip(Icons.Outlined.AccountBox, "My Office", onOfficeClick)
        SavedChip(Icons.Outlined.Place, "Danilla") {}
    }
}