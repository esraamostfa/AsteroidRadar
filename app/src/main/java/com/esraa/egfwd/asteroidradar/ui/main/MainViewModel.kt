package com.esraa.egfwd.asteroidradar.ui.main
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esraa.egfwd.asteroidradar.data.local.DBAsteroid

import com.esraa.egfwd.asteroidradar.data.network.ImageOfDay
import com.esraa.egfwd.asteroidradar.data.repository.AsteroidRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val repository: AsteroidRepository)  : ViewModel() {

    private val _imageOfDay = MutableLiveData<ImageOfDay>()
    val imageOfDay: LiveData<ImageOfDay>
    get() = _imageOfDay

    private val _asteroids = MutableLiveData<List<DBAsteroid>>()
    val asteroids: LiveData<List<DBAsteroid>>
    get() = _asteroids

    enum class APIStatus{LOADING, ERROR, DONE}

    private val _apiStatus = MutableLiveData<APIStatus>()
    val apiStatus : LiveData<APIStatus>
    get() = _apiStatus

    init {
        getImageOfDay()
        getAsteroids(AsteroidRepository.AsteroidsFilter.NEXT_WEEK)
        refreshAsteroids()
    }

    private fun refreshAsteroids() {
        _apiStatus.value = APIStatus.LOADING
        viewModelScope.launch {
            try {
                repository.refreshAsteroids()
                _apiStatus.value = APIStatus.DONE

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

    private fun getImageOfDay() {
        viewModelScope.launch {
            try {
                _imageOfDay.value = repository.getImageOfDay()

            } catch (e: java.lang.Exception) {
                Timber.e(e.toString())
            }
        }
    }
}