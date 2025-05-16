package ci.justapp.busway.presentation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

data class LocationUiModel(
    val title: String,
    val subtitle: String,
    val icon: ImageVector = Icons.Default.LocationOn,
    val onClick: () -> Unit
)
