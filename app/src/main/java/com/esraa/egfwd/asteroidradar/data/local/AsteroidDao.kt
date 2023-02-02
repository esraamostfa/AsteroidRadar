package com.esraa.egfwd.asteroidradar.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.esraa.egfwd.asteroidradar.data.models.ImageOfDay

@Dao
interface AsteroidDao {

    @Insert
    suspend fun insertImageOfDay(imageOfDay: ImageOfDay)

}