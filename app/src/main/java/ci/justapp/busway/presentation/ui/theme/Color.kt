package ci.justapp.busway.presentation.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Couleurs principales
val Orange = Color(0xFFFF5722)  // Couleur orange pour les éléments actifs
val Grey90 = Color(0xFF212121)
val Grey60 = Color(0xFF6E6E6E)
val Grey20 = Color(0xFFEEEEEE)

// Schéma de couleurs de l'application
val AppColorScheme = lightColorScheme(
    primary = Orange,
    onPrimary = Color.White,
    secondary = Grey90,
    onSecondary = Color.White,
    background = Color.White,
    onBackground = Grey90,
    surface = Color.White,
    onSurface = Grey90,
    outline = Grey60
)

