package com.esraa.egfwd.asteroidradar.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asteroids: List<DBAsteroid>)

    @Query("SELECT * FROM DBAsteroid")
    fun getAll() : LiveData<List<DBAsteroid>>

}