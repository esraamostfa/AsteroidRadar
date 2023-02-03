package com.esraa.egfwd.asteroidradar

import android.app.Application
import com.esraa.egfwd.asteroidradar.data.local.AsteroidDataBase
import com.esraa.egfwd.asteroidradar.data.repository.AsteroidRepository
import timber.log.Timber

class AsteroidApplication : Application() {

    private val database : AsteroidDataBase by lazy {
        AsteroidDataBase.getDatabase(this)
    }
    val repository : AsteroidRepository by lazy {
        AsteroidRepository(dataBase = database.dao)
    }



    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}