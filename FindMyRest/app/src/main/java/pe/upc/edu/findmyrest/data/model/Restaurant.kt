package pe.upc.edu.findmyrest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "restaurant_name")
    val name: String,

    @ColumnInfo(name = "restaurant_address")
    val address: String,

    @ColumnInfo(name = "restaurant_district")
    val district: String,

    @ColumnInfo(name = "restaurant_poster")
    val poster: String,

    @ColumnInfo(name = "restaurant_rating")
    val rating: Float
)