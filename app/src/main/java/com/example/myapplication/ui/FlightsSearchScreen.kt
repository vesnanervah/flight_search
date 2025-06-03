package com.example.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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

enum class FlightsSearchScreenState {
    unfocused, // display list of favorites
    searching, // display search airports result
    flights // display flights from selected airport
}

@Composable
fun FlightsSearchScreen() {
    var currentScreenState by remember { mutableStateOf(FlightsSearchScreenState.unfocused) }

    Scaffold(
        topBar = { FlightsSearchAppBar() }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Column {
                TextField()
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
fun UnfocusedLayout() {

}

@Composable
fun SearchingAirportsLayout() {

}

@Composable
fun FlightsResultsLayout() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightsSearchAppBar() {
    TopAppBar(
        title = { Text("Flight Search") }
    )
}


