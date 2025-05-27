package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class Airport(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "iata_code") val iataCode: String,
    val name: String,
    val passengers: Int
)

@Dao
interface AirportDao {
    @Query("SELECT * FROM Airport WHERE name LIKE '% :query' or iata_code LIKE '% :query' ")
    fun getAirports(query: String): Flow<List<Airport>>
}
