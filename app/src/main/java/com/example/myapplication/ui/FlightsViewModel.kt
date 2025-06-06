package com.example.myapplication.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
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
import kotlinx.coroutines.flow.map

class FlightsViewModel(private val flightsRepository: FlightsRepository): ViewModel() {
    val airports = flightsRepository.getAllAirports()
    val favoriteFlights = flightsRepository.getFavorites().map { convertFavoritesToFlights(it) }
    val textFieldValue = mutableStateOf("")

    fun onTextInput(newTextFieldValue: String) {
        textFieldValue.value = newTextFieldValue
        // TODO: add api request
    }

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
