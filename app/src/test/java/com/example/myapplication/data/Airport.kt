package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Airport(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "iata_code") val iataCode: String,
    val name: String,
    val passengers: Int
)
