package com.esraa.egfwd.asteroidradar.data.network

import com.esraa.egfwd.asteroidradar.Constants.API_KEY
import com.esraa.egfwd.asteroidradar.Constants.BASE_URL
import com.esraa.egfwd.asteroidradar.data.models.ImageOfDay
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface AsteroidApiService {
    @GET("planetary/apod?api_key=$API_KEY")
    suspend fun getImageOfDay(): ImageOfDay?
}


object AsteroidApi {
    val retrofitService : AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}