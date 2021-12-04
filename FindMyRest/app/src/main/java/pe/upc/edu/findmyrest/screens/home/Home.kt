package pe.upc.edu.findmyrest.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import pe.upc.edu.findmyrest.Routes

@Composable
fun Home(
    navController: NavController
) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                navController.navigate(Routes.Restaurant.route)
            }) {
            Text("Restaurants")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = {
                navController.navigate(Routes.Favorites.route)
            }) {
            Text("Favorites")
        }
    }
}