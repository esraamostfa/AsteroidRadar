package com.esraa.egfwd.asteroidradar.work
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.esraa.egfwd.asteroidradar.data.repository.AsteroidRepository
import retrofit2.HttpException

class RefreshDataWorker( private val repository: AsteroidRepository, appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            repository.refreshAsteroids()
            repository.refreshImageOfDay()
            Result.success()
        } catch (e: HttpException){
            Result.retry()
        }
}
    }
