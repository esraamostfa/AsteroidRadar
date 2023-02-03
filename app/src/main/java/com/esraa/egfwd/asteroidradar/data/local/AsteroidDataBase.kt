package com.esraa.egfwd.asteroidradar.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DBAsteroid::class], version = 1)
abstract class AsteroidDataBase : RoomDatabase() {

        abstract val dao: AsteroidDao

        companion object {
            private lateinit var INSTANCE : AsteroidDataBase

            fun getDatabase(context: Context) : AsteroidDataBase{

                synchronized(AsteroidDataBase::class.java){
                    if(!::INSTANCE.isInitialized) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AsteroidDataBase::class.java,
                            "asteroids")
                            .build()
                    }}

                return INSTANCE
            }

        }

    }