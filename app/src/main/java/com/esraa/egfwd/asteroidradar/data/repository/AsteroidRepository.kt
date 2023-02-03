package com.esraa.egfwd.asteroidradar.data.repository
import android.util.Log
import com.esraa.egfwd.asteroidradar.data.local.AsteroidDao
import com.esraa.egfwd.asteroidradar.data.local.DBAsteroid
import com.esraa.egfwd.asteroidradar.data.network.AsteroidApi
import com.esraa.egfwd.asteroidradar.data.network.ImageOfDay
import com.esraa.egfwd.asteroidradar.data.network.asDBAsteroid
import com.esraa.egfwd.asteroidradar.today
import com.esraa.egfwd.asteroidradar.weekDates
import com.esraa.egfwd.asteroidradar.weekDatesString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AsteroidRepository(val dataBase: AsteroidDao) {

    suspend fun getImageOfDay(): ImageOfDay? {
        val imageOfDay = AsteroidApi.retrofitService.getImageOfDay()
        return if(imageOfDay?.mediaType == "image") {
            imageOfDay
        } else
            null
    }

    enum class AsteroidsFilter(val value: String) { SAVED("saved_asteroids"), TODAY("today"), NEXT_WEEK("next_week") }

    suspend fun getAsteroids(filter: AsteroidsFilter) : List<DBAsteroid> {

        val asteroids = when(filter) {
            AsteroidsFilter.NEXT_WEEK -> dataBase.getWeek(weekDatesString)
            AsteroidsFilter.TODAY -> dataBase.getToday(today)
            AsteroidsFilter.SAVED -> dataBase.getAll()

        }

        return asteroids
    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            //get asteroids from api
            val response = AsteroidApi.retrofitService.getAsteroids()

            //convert response to list of asteroids and insert in DB
            for (day in weekDatesString) {
                val asteroids = response.nearEarthObjects[day] ?: listOf()
                dataBase.insertAll(asteroids.asDBAsteroid())
            }


        }
    }
}