package pe.upc.edu.findmyrest.data.local

import androidx.room.*
import pe.upc.edu.findmyrest.data.model.Restaurant

@Dao
interface RestaurantDao {

    @Query("select * from restaurants")
    fun fetchRestaurants(): List<Restaurant>

    @Query("select * from restaurants where id =:id")
    fun fetchById(id: Int): List<Restaurant>

    @Insert
    fun insert(restaurant: Restaurant)

    @Delete
    fun delete(restaurant: Restaurant)

    @Update
    fun update(restaurant: Restaurant)
}