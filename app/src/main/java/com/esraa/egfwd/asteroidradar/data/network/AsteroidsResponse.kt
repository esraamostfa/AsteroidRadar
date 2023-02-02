package com.esraa.egfwd.asteroidradar.data.network

import com.esraa.egfwd.asteroidradar.data.models.Asteroid
import com.squareup.moshi.Json

data class AsteroidsResponse(

    @Json(name= "near_earth_objects")
    val nearEarthObjects: Map<String, List<Asteroid>>
)
