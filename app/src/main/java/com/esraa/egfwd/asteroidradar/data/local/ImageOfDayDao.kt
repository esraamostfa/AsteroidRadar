package com.esraa.egfwd.asteroidradar.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.esraa.egfwd.asteroidradar.data.network.ImageOfDay

@Dao
interface ImageOfDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(image: ImageOfDay)

    @Query("DELETE FROM ImageOfDay ")
    suspend fun clear()

    @Query("SELECT * FROM ImageOfDay LIMIT 1")
    fun get() : LiveData<ImageOfDay>

}