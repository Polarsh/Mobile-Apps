package pe.upc.edu.findmyrest.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import pe.upc.edu.findmyrest.data.local.AppDatabase
import pe.upc.edu.findmyrest.data.model.Restaurant

@Composable
fun Favorite() {
    val context = LocalContext.current
    val restaurants = remember { mutableStateOf(listOf<Restaurant>()) }
    restaurants.value = AppDatabase.getInstance(context).RestaurantDao().fetchRestaurants()

    Scaffold {
        RestaurantList(restaurants.value) { restaurant ->
            restaurants.value = restaurants.value.filter { it != restaurant }.toMutableList()
        }
    }
}


@Composable
fun RestaurantList(restaurants: List<Restaurant>, deleteRestaurant: (Restaurant) -> Unit) {
    LazyColumn {
        items(restaurants) { restaurant ->
            RestaurantRow(restaurant) {
                deleteRestaurant(it)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RestaurantRow(restaurant: Restaurant, deleteRestaurant: (Restaurant) -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            RestaurantImage(
                restaurant=restaurant
            )
            Spacer(Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(7f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    restaurant.name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    restaurant.district,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    restaurant.address,
                    style = MaterialTheme.typography.caption
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .size(32.dp),
                onClick = {
                    AppDatabase.getInstance(context).RestaurantDao().delete(restaurant)
                    deleteRestaurant(restaurant)
                }) {
                Icon(Icons.Filled.Delete, null)
            }
        }
    }
}

@Composable
fun RestaurantImage(restaurant: Restaurant) {
    Image(
        painter = rememberImagePainter(
            data = restaurant.poster,
        ),
        contentDescription = "Restaurant photo",
        modifier = Modifier
            .size(64.dp)
            .clip(shape = RoundedCornerShape(4.dp)),
        contentScale = ContentScale.Crop
    )
}