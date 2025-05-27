package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false)
abstract class FlightsDatabase: RoomDatabase() {
    abstract fun airportDao(): AirportDao

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var Instance: FlightsDatabase? = null;

        fun getDatabase(context: Context): FlightsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FlightsDatabase::class.java, "flights_database").createFromAsset("flight_search.db").fallbackToDestructiveMigration(true).build().also {
                        Instance = it
                    }
            }
        }
    }
}