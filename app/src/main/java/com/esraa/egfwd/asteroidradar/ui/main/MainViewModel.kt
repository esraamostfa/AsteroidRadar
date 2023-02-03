package com.esraa.egfwd.asteroidradar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esraa.egfwd.asteroidradar.data.local.DBAsteroid
import com.esraa.egfwd.asteroidradar.data.repository.AsteroidRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val repository: AsteroidRepository)  : ViewModel() {

    val imageOfDay = repository.imageOfDay

    private val _asteroids = MutableLiveData<List<DBAsteroid>>()
    val asteroids: LiveData<List<DBAsteroid>>
    get() = _asteroids

    enum class APIStatus{LOADING, ERROR, DONE}

    private val _apiStatus = MutableLiveData<APIStatus>()
    val apiStatus : LiveData<APIStatus>
    get() = _apiStatus

    init {
        refreshImageOfDay()
        refreshAsteroids()
         }

    private fun refreshAsteroids() {
        _apiStatus.value = APIStatus.LOADING
        viewModelScope.launch {
            try {
                repository.refreshAsteroids()
                _apiStatus.value = APIStatus.DONE
                getAsteroids(AsteroidRepository.AsteroidsFilter.NEXT_WEEK)

            } catch (e: java.lang.Exception) {
                _apiStatus.value = APIStatus.ERROR
                Timber.e(e.toString())
            }
        }
    }

    private fun getAsteroids(filter: AsteroidRepository.AsteroidsFilter){
        viewModelScope.launch {
            _asteroids.value = repository.getAsteroids(filter)
        }
    }


     fun filterAsteroids(filter: AsteroidRepository.AsteroidsFilter) {
        getAsteroids(filter)
    }

    private fun refreshImageOfDay() {
        viewModelScope.launch {
            try {
                repository.refreshImageOfDay()

            } catch (e: java.lang.Exception) {
                Timber.e(e.toString())
            }
        }
    }


    private val _navigateToSelectedAsteroid = MutableLiveData<DBAsteroid?>()

    val navigateToSelectedAsteroid: LiveData<DBAsteroid?>
        get() = _navigateToSelectedAsteroid

    fun displayAsteroidDetails(asteroid: DBAsteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }
}