package com.example.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.Flight

enum class FlightsSearchScreenState {
    unfocused, // display list of favorites
    searching, // display search airports result
    flights // display flights from selected airport
}

@Composable
fun FlightsSearchScreen(
    viewModel: FlightsViewModel = viewModel(factory = FlightsViewModel.factory)
) {
    val currentScreenState by remember { mutableStateOf(FlightsSearchScreenState.unfocused) }
    val textFieldValue by remember { viewModel.textFieldValue }

    Scaffold(
        topBar = { FlightsSearchAppBar() }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Column {
                TextField(
                    textFieldValue,
                    { viewModel.onTextInput(it) },
                )
                when (currentScreenState) {
                    FlightsSearchScreenState.unfocused -> UnfocusedLayout()
                    FlightsSearchScreenState.searching -> SearchingAirportsLayout()
                    FlightsSearchScreenState.flights -> FlightsResultsLayout()
                }
            }
        }
    }
}

@Composable
private fun UnfocusedLayout() {

}

@Composable
private fun SearchingAirportsLayout() {

}

// TODO: replace in separate file with FlightCard
@Composable
fun FlightsList(title: String, flights: List<Flight>, onFavoriteToggle: (Flight) -> Unit) {
    Column {
        Text(title)
        LazyColumn {
            // TODO: isFavoriteCheck
           items(flights) { FlightCard(it, false, onFavoriteToggle)  }
        }
    }
}

@Composable
private fun FlightsResultsLayout() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FlightsSearchAppBar() {
    TopAppBar(
        title = { Text("Flight Search") }
    )
}


