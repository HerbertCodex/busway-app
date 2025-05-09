package ci.justapp.busway.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun PreviousSearchSection(
    onSelect: (String) -> Unit
) = FlatCard {
    Column {
        PreviousSearchItem("Monumen Nasional") { onSelect("Monumen Nasional") }
        ThinDivider()
        PreviousSearchItem("Central Park Mall") { onSelect("Central Park Mall") }
        ThinDivider()
        PreviousSearchItem("Stasiun Gambir") { onSelect("Stasiun Gambir") }
    }
}
