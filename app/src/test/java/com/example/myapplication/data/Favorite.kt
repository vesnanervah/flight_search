package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Favorite(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "departure_code") val departureCode: String,
    @ColumnInfo(name = "destination_code") val destinationCode: String,
)

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorite")
    fun getFavorites()
}

