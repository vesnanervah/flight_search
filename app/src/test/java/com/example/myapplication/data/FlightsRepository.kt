package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
    fun getAirports(query: String): Flow<List<Airport>>

    fun getFavorites(): Flow<List<Favorite>>

    suspend fun saveFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)
}


class LocalFlightsRepository(
    private val airportDao: AirportDao,
    private val favoriteDao: FavoriteDao
): FlightsRepository {
    override fun getAirports(query: String): Flow<List<Airport>> = airportDao.getAirports(query)

    override fun getFavorites(): Flow<List<Favorite>> = favoriteDao.getFavorites()

    override suspend fun saveFavorite(favorite: Favorite) = favoriteDao.insert(favorite)

    override suspend fun deleteFavorite(favorite: Favorite) = favoriteDao.delete(favorite)
}
