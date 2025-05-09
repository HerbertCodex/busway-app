package ci.justapp.busway.presentation.navigation

import SearchDestinationScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ci.justapp.busway.presentation.screens.destinationDetails.DestinationDetailsScreen
import ci.justapp.busway.presentation.screens.main.MainScreen
import ci.justapp.busway.presentation.screens.main.MainViewModel
import ci.justapp.busway.presentation.screens.result.SearchResultsScreen

// Définition des routes de navigation
object NavRoutes {
    const val MAIN = "main"
    const val SEARCH_DESTINATION = "search_destination"
    const val SEARCH_RESULTS = "search_results"
    const val DESTINATION_DETAILS = "destination_details"
    const val SETTINGS = "settings"
    const val SAVED = "saved"
}

@Composable
fun NavigationApp(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.MAIN,
        modifier = modifier
    ) {
        // Écran principal avec la carte
        composable(NavRoutes.MAIN) {
            MainScreen(
                onSearchRequested = { prefill ->
                    navController.navigate("${NavRoutes.SEARCH_DESTINATION}?${NavArg.PREFILL}=$prefill")
                },
                viewModel = mainViewModel,
            )
        }

        // Écran de recherche de destination
        composable(NavRoutes.SEARCH_DESTINATION) {
            SearchDestinationScreen(
                initialQuery = "",
                onBackClick = {
                    navController.popBackStack()
                },
                onSearchClick = {
                    navController.navigate(NavRoutes.SEARCH_RESULTS)
                },
                onLocationSelect = { destination ->
                    navController.navigate("${NavRoutes.DESTINATION_DETAILS}/$destination")
                }
            )
        }

        // Écran des résultats de recherche
        composable("${NavRoutes.SEARCH_RESULTS}?query={query}") { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            SearchResultsScreen(
                searchQuery = query,
                onBackClick = {
                    navController.popBackStack()
                },
                onResultClick = { destination ->
                    navController.navigate("${NavRoutes.DESTINATION_DETAILS}/$destination")
                }
            )
        }

        // Écran de détails de la destination
        composable("${NavRoutes.DESTINATION_DETAILS}/{destination}") { backStackEntry ->
            val destination = backStackEntry.arguments?.getString("destination") ?: ""
            DestinationDetailsScreen(
                destination = destination,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
