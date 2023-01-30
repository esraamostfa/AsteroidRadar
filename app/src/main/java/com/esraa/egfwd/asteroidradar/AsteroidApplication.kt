package com.esraa.egfwd.asteroidradar

import android.app.Application
import com.esraa.egfwd.asteroidradar.data.repository.AsteroidRepository
import timber.log.Timber

class AsteroidApplication : Application() {

    val repository : AsteroidRepository by lazy {
        AsteroidRepository()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}