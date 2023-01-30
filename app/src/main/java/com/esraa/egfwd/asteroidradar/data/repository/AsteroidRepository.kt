package com.esraa.egfwd.asteroidradar.data.repository

import com.esraa.egfwd.asteroidradar.data.models.ImageOfDay
import com.esraa.egfwd.asteroidradar.data.network.AsteroidApi

class AsteroidRepository {

    suspend fun getImageOfDay(): ImageOfDay? {
        val imageOfDay = AsteroidApi.retrofitService.getImageOfDay()
        return if(imageOfDay?.mediaType == "image") {
            imageOfDay
        } else
            null
    }
}