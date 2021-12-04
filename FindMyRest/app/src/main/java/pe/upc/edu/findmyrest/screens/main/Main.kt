package pe.upc.edu.findmyrest.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import pe.upc.edu.findmyrest.Routes
import pe.upc.edu.findmyrest.screens.favorites.Favorite
import pe.upc.edu.findmyrest.screens.home.Home
import pe.upc.edu.findmyrest.screens.restaurant.Restaurant

@Composable
fun Main() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) { Home(navController) }
        composable(Routes.Restaurant.route) { Restaurant() }
        composable(Routes.Favorites.route) { Favorite() }
    }
}