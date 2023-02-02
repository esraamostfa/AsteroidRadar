package com.esraa.egfwd.asteroidradar.ui.main
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esraa.egfwd.asteroidradar.data.models.Asteroid
import com.esraa.egfwd.asteroidradar.data.models.ImageOfDay
import com.esraa.egfwd.asteroidradar.data.repository.AsteroidRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val repository: AsteroidRepository)  : ViewModel() {

    private val _imageOfDay = MutableLiveData<ImageOfDay>()
    val imageOfDay: LiveData<ImageOfDay>
    get() = _imageOfDay

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
    get() = _asteroids

    init {
        Timber.i("MainViewModel was initialized")
        getImageOfDay()
        getAsteroids()
    }

    private fun getAsteroids() {
        viewModelScope.launch {
            try {

                _asteroids.value = repository.getAsteroids()

            } catch (e: java.lang.Exception) {
                Timber.e(e.toString())
            }
        }
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