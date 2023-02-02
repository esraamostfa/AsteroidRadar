package com.esraa.egfwd.asteroidradar.data.repository
import com.esraa.egfwd.asteroidradar.Constants.API_QUERY_DATE_FORMAT
import com.esraa.egfwd.asteroidradar.data.models.Asteroid
import com.esraa.egfwd.asteroidradar.data.models.ImageOfDay
import com.esraa.egfwd.asteroidradar.data.network.AsteroidApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AsteroidRepository {

    suspend fun getImageOfDay(): ImageOfDay? {
        val imageOfDay = AsteroidApi.retrofitService.getImageOfDay()
        return if(imageOfDay?.mediaType == "image") {
            imageOfDay
        } else
            null
    }

    suspend fun getAsteroids(): List<Asteroid> {
        val response = AsteroidApi.retrofitService.getAsteroids()
        val formatter = DateTimeFormatter.ofPattern(API_QUERY_DATE_FORMAT)
        val today = LocalDateTime.now().format(formatter).toString()
       return response.nearEarthObjects[today] ?: listOf()

    }
}