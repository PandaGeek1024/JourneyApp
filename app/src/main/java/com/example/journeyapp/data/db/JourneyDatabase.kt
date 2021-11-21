package com.example.journeyapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1)
abstract class JourneyDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        fun getInstance(context: Context): JourneyDatabase {
            return Room.databaseBuilder(
                context,
                JourneyDatabase::class.java, "journey.db"
            ).build()
        }
    }
}