package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface FlightsRepository {
    fun getAllAirports(): Flow<List<Airport>>

    fun getAirportsByQuery(query: String): Flow<List<Airport>>

    fun getAirportByIata(iataCode: String): Flow<Airport?>

    fun getFavorites(): Flow<List<Favorite>>

    suspend fun saveFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)
}


class LocalFlightsRepository(
    private val airportDao: AirportDao,
    private val favoriteDao: FavoriteDao
): FlightsRepository {
    override fun getAllAirports(): Flow<List<Airport>> = airportDao.getAllAirports()

    override fun getAirportsByQuery(query: String): Flow<List<Airport>> = airportDao.getAirports(query)

    override fun getAirportByIata(iataCode: String): Flow<Airport?> = airportDao.getAirportsByIata(iataCode).map {
        if (it.size > 1 ){
            throw Exception("Iata code should be unique")
        }
        it.firstOrNull()
    }

    override fun getFavorites(): Flow<List<Favorite>> = favoriteDao.getFavorites()

    override suspend fun saveFavorite(favorite: Favorite) = favoriteDao.insert(favorite)

    override suspend fun deleteFavorite(favorite: Favorite) = favoriteDao.delete(favorite)
}
