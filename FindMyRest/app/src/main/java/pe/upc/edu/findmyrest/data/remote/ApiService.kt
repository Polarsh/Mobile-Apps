package pe.upc.edu.findmyrest.data.remote

import pe.upc.edu.findmyrest.data.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("restaurants")
    fun fetchRestaurants() : Call<List<Restaurant>>
}