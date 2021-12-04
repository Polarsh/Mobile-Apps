package pe.upc.edu.findmyrest.screens.restaurant

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import pe.upc.edu.findmyrest.data.remote.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Restaurant() {
    val restaurants = remember { mutableStateOf(listOf<Restaurant>()) }
    val apiService = ApiClient.build()
    val fetchRestaurants = apiService.fetchRestaurants()

    fetchRestaurants.enqueue(object : Callback<List<Restaurant>> {
        override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
            Log.d("Restaurant", response.body().toString())

            if (response.isSuccessful) {
                restaurants.value = response.body()!!
            }
        }

        override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
            Log.d("Restaurant", t.toString())
        }

    })

    Scaffold {
        RestaurantList(restaurants.value)
    }
}


@Composable
fun RestaurantList(restaurants: List<Restaurant>) {
    LazyColumn {
        items(restaurants) { restaurant ->
            RestaurantRow(restaurant)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RestaurantRow(restaurant: Restaurant) {
    val context = LocalContext.current
    val favorite = remember { mutableStateOf(false) }
    favorite.value = (AppDatabase.getInstance(context).RestaurantDao().fetchById(restaurant.id).isNotEmpty())
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(restaurant.poster),
                contentDescription = null,
                modifier = Modifier
                    .size(92.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(7f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    restaurant.name,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .size(32.dp),
                onClick = {
                    if (favorite.value)
                        AppDatabase.getInstance(context).RestaurantDao().delete(restaurant)
                    else
                        AppDatabase.getInstance(context).RestaurantDao().insert(restaurant)
                    favorite.value = !favorite.value
                }) {

                Icon(
                    Icons.Filled.Favorite,
                    "",
                    tint = if (favorite.value) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
    }
}


