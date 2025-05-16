package ci.justapp.busway.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun MapContainer(content: @Composable () -> Unit) {
    OutlinedCard(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFCFCFCF)), // filet, pas d’ombre
        elevation = CardDefaults.outlinedCardElevation(0.dp), // 0 = aucune ombre
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White                      // ou #F8F8F8 si tu veux
        )
    ) { content() }
}

