package com.esraa.egfwd.asteroidradar.data.network

import com.squareup.moshi.Json

data class ImageOfDay(
    @Json(name = "media_type") val mediaType: String,
    val title: String,
    val url: String
    )
