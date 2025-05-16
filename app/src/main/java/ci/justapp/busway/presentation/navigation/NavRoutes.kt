package ci.justapp.busway.presentation.navigation

/** Graphs de premier niveau (= chaque onglet) */
object RootGraph {
    const val HOME = "home_graph"
    const val SEARCH = "search_graph"
    const val SAVED = "saved_graph"
    const val SETTINGS = "settings_graph"
}

/** Routes internes à HOME */
object HomeDest {
    const val MAIN = "home_main"
}

/** Routes internes à SEARCH */
object SearchDest {
    const val MAIN = "search_main"
    const val RESULTS = "search_results"
    const val DETAIL = "destination_details"
}

/** Args communs */
object NavArg {
    const val PREFILL = "prefill"
    const val DESTINATION = "destination"
}