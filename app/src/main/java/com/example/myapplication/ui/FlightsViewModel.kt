package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.MainApplication
import com.example.myapplication.data.Favorite
import com.example.myapplication.data.Flight
import com.example.myapplication.data.FlightsRepository
import kotlinx.coroutines.flow.firstOrNull

class FlightsViewModel(private val flightsRepository: FlightsRepository): ViewModel() {
    val airports = flightsRepository.getAllAirports()

    private suspend fun convertFavoritesToFlights(favorites: List<Favorite>): List<Flight> {
        return favorites.mapNotNull {
            val departure = flightsRepository.getAirportByIata(it.departureCode).firstOrNull()
            val destination = flightsRepository.getAirportByIata(it.destinationCode).firstOrNull()
            if (departure != null && destination != null) {
                 Flight(departure, destination)
            }
            null
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY]) as MainApplication
                FlightsViewModel(application.flightsRepository)
            }
        }
    }
}
