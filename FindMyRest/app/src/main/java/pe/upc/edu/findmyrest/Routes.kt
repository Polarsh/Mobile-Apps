package pe.upc.edu.findmyrest

sealed class Routes(val route: String) {
    object Home: Routes("Home")
    object Restaurant: Routes("Restaurant")
    object Favorites: Routes("Favorites")
}