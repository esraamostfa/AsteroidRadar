package com.esraa.egfwd.asteroidradar.data.repository
import com.esraa.egfwd.asteroidradar.data.local.AsteroidDataBase
import com.esraa.egfwd.asteroidradar.data.local.DBAsteroid
import com.esraa.egfwd.asteroidradar.data.network.AsteroidApi
import com.esraa.egfwd.asteroidradar.data.network.asDBAsteroid
import com.esraa.egfwd.asteroidradar.today
import com.esraa.egfwd.asteroidradar.weekDatesString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AsteroidRepository(val dataBase: AsteroidDataBase) {

    val imageOfDay = dataBase.imageDao.get()
    suspend fun refreshImageOfDay() {
        val imageOfDay = AsteroidApi.retrofitService.getImageOfDay()

         if(imageOfDay?.mediaType == "image") {
             dataBase.imageDao.clear()
             dataBase.imageDao.insert(imageOfDay)
         }
    }

    enum class AsteroidsFilter(val value: String) { SAVED("saved_asteroids"), TODAY("today"), NEXT_WEEK("next_week") }

    suspend fun getAsteroids(filter: AsteroidsFilter) : List<DBAsteroid> {

        val asteroids = when(filter) {
            AsteroidsFilter.NEXT_WEEK -> dataBase.dao.getWeek(weekDatesString)
            AsteroidsFilter.TODAY -> dataBase.dao.getToday(today)
            AsteroidsFilter.SAVED -> dataBase.dao.getAll()

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
                dataBase.dao.insertAll(asteroids.asDBAsteroid())
            }


        }
    }
}