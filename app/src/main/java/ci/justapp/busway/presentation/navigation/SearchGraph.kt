package ci.justapp.busway.presentation.navigation

import SearchDestinationScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import ci.justapp.busway.presentation.screens.destinationDetails.DestinationDetailsScreen
import ci.justapp.busway.presentation.screens.result.SearchResultsScreen

fun NavGraphBuilder.searchGraph(rootNav: NavHostController) {

    navigation(
        startDestination = "${SearchDest.MAIN}?${NavArg.PREFILL}={${NavArg.PREFILL}}",
        route = RootGraph.SEARCH
    ) {

        /* -- page principale Search -- */
        composable(
            route = "${SearchDest.MAIN}?${NavArg.PREFILL}={${NavArg.PREFILL}}",
            arguments = listOf(
                navArgument(NavArg.PREFILL) {
                    defaultValue = ""; nullable = true
                }
            )
        ) { backStack ->
            val initial = backStack.arguments?.getString(NavArg.PREFILL) ?: ""
            var searchQuery by remember { mutableStateOf(initial) }

            SearchDestinationScreen(
                initialQuery = initial,
                onBackClick = {
                    // Naviguer vers l'écran HOME si on est à la racine du graphe SEARCH
                    if (rootNav.previousBackStackEntry == null) {
                        rootNav.navigate(RootGraph.HOME) {
                            popUpTo(RootGraph.SEARCH) { inclusive = true }
                        }
                    } else {
                        rootNav.popBackStack()
                    }
                },
                onSearchClick = {
                    // Navigation vers les résultats de recherche avec la requête actuelle
                    rootNav.navigate("${SearchDest.RESULTS}?query=$searchQuery")
                },
                onLocationSelect = { dest ->
                    rootNav.navigate("${SearchDest.DETAIL}/$dest")
                }
            )
        }

        /* -- résultats de recherche -- */
        composable(
            route = "${SearchDest.RESULTS}?query={query}",
            arguments = listOf(
                navArgument("query") {
                    defaultValue = ""; nullable = true
                }
            )
        ) { backStack ->
            val query = backStack.arguments?.getString("query") ?: ""
            SearchResultsScreen(
                searchQuery = query,
                onBackClick = { rootNav.popBackStack() },
                onResultClick = { destination ->
                    rootNav.navigate("${SearchDest.DETAIL}/$destination")
                },
                onNewSearch = { newQuery ->
                    // Navigation vers le même écran mais avec la nouvelle requête
                    rootNav.navigate("${SearchDest.RESULTS}?query=$newQuery") {
                        // Remplacer l'entrée actuelle dans le back stack pour éviter d'empiler les écrans
                        popUpTo("${SearchDest.RESULTS}?query=$query") { inclusive = true }
                    }
                }
            )
        }

        /* -- détail d'une destination -- */
        composable(
            route = "${SearchDest.DETAIL}/{${NavArg.DESTINATION}}",
            arguments = listOf(navArgument(NavArg.DESTINATION) { defaultValue = "" })
        ) { back ->
            val dest = back.arguments?.getString(NavArg.DESTINATION) ?: ""
            DestinationDetailsScreen(
                destination = dest,
                onBackClick = { rootNav.popBackStack() }
            )
        }
    }
}
