package ci.justapp.busway.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SavedChip(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit            // ← action reçue de l’appelant
) {
    FilterChip(
        selected = false,
        onClick = onClick,         // ← on la passe directement à FilterChip
        label = { Text(label) },
        leadingIcon = {
            Icon(
                icon, null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        shape = RoundedCornerShape(20.dp),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = false,
            borderColor = MaterialTheme.colorScheme.outline,
            borderWidth = 1.dp
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.Transparent,
            selectedContainerColor =
                MaterialTheme.colorScheme.primary.copy(alpha = .12f),
            labelColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

