package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.FlightsDatabase
import com.example.myapplication.data.FlightsRepository
import com.example.myapplication.data.LocalFlightsRepository

class MainApplication: Application() {
    lateinit var database: FlightsDatabase
    lateinit var flightsRepository: FlightsRepository

    override fun onCreate() {
        super.onCreate()
        database = FlightsDatabase.getDatabase(this)
        flightsRepository = LocalFlightsRepository(database.airportDao(), database.favoriteDao())
    }
}