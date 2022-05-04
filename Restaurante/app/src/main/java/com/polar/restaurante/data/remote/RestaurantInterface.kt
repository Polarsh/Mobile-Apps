package com.polar.restaurante.data.remote

import com.polar.restaurante.data.entities.Restaurant

import retrofit2.Call
import retrofit2.http.GET

interface RestaurantInterface {

    @GET("restaurants")
    fun fetchRestaurants(): Call<List<Restaurant>>
}