package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
    fun getAirports(query: String): Flow<List<Airport>>

    fun getFavorites(): Flow<List<Favorite>>

    fun getAirportsFromFavorites(): Flow<List<Airport>>
}

