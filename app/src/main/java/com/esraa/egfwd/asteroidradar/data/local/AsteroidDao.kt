package com.esraa.egfwd.asteroidradar.data.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asteroids: List<DBAsteroid>)

    @Query("SELECT * FROM DBAsteroid ORDER BY close_approach_date ")
    suspend fun getAll() : List<DBAsteroid>

    @Query("SELECT * FROM DBAsteroid WHERE close_approach_date IN (:weekDates) ORDER BY close_approach_date")
    suspend fun getWeek(weekDates: List<String>) : List<DBAsteroid>

    @Query("SELECT * FROM DBAsteroid WHERE close_approach_date = :today")
    suspend fun getToday(today: String) : List<DBAsteroid>

}