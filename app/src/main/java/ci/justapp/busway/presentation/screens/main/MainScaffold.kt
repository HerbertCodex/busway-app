package ci.justapp.busway.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import ci.justapp.busway.presentation.components.currentRoute
import ci.justapp.busway.presentation.navigation.HomeDest
import ci.justapp.busway.presentation.navigation.NavArg
import ci.justapp.busway.presentation.navigation.RootGraph
import ci.justapp.busway.presentation.navigation.searchGraph
import ci.justapp.busway.presentation.ui.theme.Grey60
import ci.justapp.busway.presentation.ui.theme.Orange

private data class BottomTab(val graphRoute: String, val icon: ImageVector, val label: String)

@Composable
fun MainScaffold() {
    val rootNav = rememberNavController()

    /* ---- items de la barre ---- */
    val tabs = listOf(
        BottomTab(RootGraph.HOME, Icons.Outlined.Home, "Home"),
        BottomTab(RootGraph.SEARCH, Icons.Outlined.Search, "Search"),
        BottomTab(RootGraph.SAVED, Icons.Outlined.Star, "Saved"),
        BottomTab(RootGraph.SETTINGS, Icons.Outlined.Settings, "Settings"),
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                tonalElevation = 0.dp,
                containerColor = Color.White
            ) {
                val current = currentRoute(rootNav)
                tabs.forEach { tab ->
                    val isSelected = current?.startsWith(tab.graphRoute) == true

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            if (!isSelected) {
                                rootNav.navigate(tab.graphRoute) {
                                    popUpTo(rootNav.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = null,
                                tint = if (isSelected) Orange else Grey60
                            )
                        },
                        label = {
                            Text(
                                text = tab.label,
                                color = if (isSelected) Orange else Grey60
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Orange,
                            selectedTextColor = Orange,
                            indicatorColor = Orange.copy(.12f),
                            unselectedIconColor = Grey60,
                            unselectedTextColor = Grey60
                        )
                    )
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = rootNav,
            startDestination = RootGraph.HOME,
            modifier = Modifier.padding(padding)
        ) {

            /* ------- graph HOME ------- */
            navigation(
                startDestination = HomeDest.MAIN,
                route = RootGraph.HOME
            ) {
                composable(HomeDest.MAIN) {
                    MainScreen(
                        onSearchRequested = { prefill ->
                            rootNav.navigate("${RootGraph.SEARCH}?${NavArg.PREFILL}=$prefill") {
                                popUpTo(rootNav.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }

            /* ------- graph SEARCH ------- */
            searchGraph(rootNav)

            /* ------- graphs SAVED / SETTINGS place-holders ------- */
            navigation(
                startDestination = "saved_main",
                route = RootGraph.SAVED
            ) {
                composable("saved_main") {
                    // Placeholder pour l'écran des favoris
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Saved Destinations - Coming Soon")
                    }
                }
            }

            navigation(
                startDestination = "settings_main",
                route = RootGraph.SETTINGS
            ) {
                composable("settings_main") {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Settings - Coming Soon")
                    }
                }
            }
        }
    }
}
