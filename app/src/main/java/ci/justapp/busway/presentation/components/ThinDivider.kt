package ci.justapp.busway.presentation.components

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ThinDivider() = Divider(
    thickness = 1.dp,
    color = MaterialTheme.colorScheme.outline.copy(alpha = .5f)
)
