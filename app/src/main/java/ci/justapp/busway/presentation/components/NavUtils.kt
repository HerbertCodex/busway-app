import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ci.justapp.busway.presentation.navigation.RootGraph
import ci.justapp.busway.presentation.navigation.SearchDest

/**
 * Récupère la route actuelle du NavController
 */
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    return navBackStackEntry?.destination?.route?.let { route ->
        // Extraire la partie principale de la route (avant les arguments)
        val mainRoute = when {
            route.contains("?") -> route.split("?")[0]
            route.contains("/") -> route.split("/")[0]
            else -> route
        }

        // Pour les routes imbriquées, retourner la route parente
        when {
            mainRoute == SearchDest.MAIN ||
                    mainRoute == SearchDest.RESULTS ||
                    mainRoute == SearchDest.DETAIL -> RootGraph.SEARCH

            else -> mainRoute
        }
    }
}