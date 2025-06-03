package com.example.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.Flight

@Composable
fun FlightCard(flight: Flight, isFavorite: Boolean, onFavoriteToggle: (Flight) -> Unit, modifier: Modifier = Modifier) {
    Card(modifier.padding(8.dp)) {
        Row {
            // TODO: styles
            Column {
                Text("Depart")
                Row {
                    Text(flight.depart.iataCode)
                    Text(flight.depart.name)
                }
                Text("Arrive")
                Row {
                    Text(flight.arrive.iataCode)
                    Text(flight.arrive.name)
                }
            }
            Button(
                onClick = { onFavoriteToggle(flight) }
            ) {
                Icon(Icons.Default.Favorite, "Add to favorite")
            }
        }
    }
}
