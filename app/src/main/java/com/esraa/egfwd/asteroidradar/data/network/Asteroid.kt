package com.esraa.egfwd.asteroidradar.data.network

import com.esraa.egfwd.asteroidradar.data.local.DBAsteroid
import com.squareup.moshi.Json

data class Asteroid(
    val id: Long,
    @Json(name = "name")
    val codename: String,
    @Json(name = "close_approach_data")
    val closeApproachData: List<Map<String, Any>>,

    @Json(name = "absolute_magnitude_h")
    val absoluteMagnitude: Double,

    @Json(name = "estimated_diameter")
    val estimatedDiameter: Map<String, Map<String, Double>>,

    @Json(name = "is_potentially_hazardous_asteroid")
    val isPotentiallyHazardous: Boolean
) {

    val estimatedDiameterMax = estimatedDiameter["kilometers"]?.get("estimated_diameter_max")
    private val missDistance = closeApproachData[0]["miss_distance"] as Map<*, *>
    val missDistanceAstro = this.missDistance["astronomical"]
    private val relativeVelocity = closeApproachData[0]["relative_velocity"] as Map<*, *>
    val relativeVelocityPerSecond = this.relativeVelocity["kilometers_per_second"]
    val closeApproachDate = closeApproachData[0]["close_approach_date"]



}

fun List<Asteroid>.asDBAsteroid() : List<DBAsteroid> {
    return map {
        DBAsteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate.toString(),
            relativeVelocity= it.relativeVelocityPerSecond.toString(),
            missDistance = it.missDistanceAstro.toString(),
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter= it.estimatedDiameterMax?:0.0,
            isPotentiallyHazardous = it.isPotentiallyHazardous

        )
    }
}

