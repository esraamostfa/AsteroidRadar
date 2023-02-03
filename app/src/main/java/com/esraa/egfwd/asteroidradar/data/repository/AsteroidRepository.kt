package com.esraa.egfwd.asteroidradar.data.repository
import androidx.lifecycle.LiveData
import com.esraa.egfwd.asteroidradar.Constants.API_QUERY_DATE_FORMAT
import com.esraa.egfwd.asteroidradar.data.local.AsteroidDao
import com.esraa.egfwd.asteroidradar.data.local.DBAsteroid
import com.esraa.egfwd.asteroidradar.data.network.ImageOfDay
import com.esraa.egfwd.asteroidradar.data.network.AsteroidApi
import com.esraa.egfwd.asteroidradar.data.network.asDBAsteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AsteroidRepository(val dataBase: AsteroidDao) {

    suspend fun getImageOfDay(): ImageOfDay? {
        val imageOfDay = AsteroidApi.retrofitService.getImageOfDay()
        return if(imageOfDay?.mediaType == "image") {
            imageOfDay
        } else
            null
    }

    val asteroids: LiveData<List<DBAsteroid>> = dataBase.getAll()

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            //get asteroids from api
            val response = AsteroidApi.retrofitService.getAsteroids()

            //get date of today
            val formatter = DateTimeFormatter.ofPattern(API_QUERY_DATE_FORMAT)
            val today = LocalDateTime.now().format(formatter).toString()

            //convert response to list of asteroids
            val asteroids = response.nearEarthObjects[today] ?: listOf()

            dataBase.insertAll(asteroids.asDBAsteroid())
        }
    }
}