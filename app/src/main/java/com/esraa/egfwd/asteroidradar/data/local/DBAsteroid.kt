package com.esraa.egfwd.asteroidradar.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class DBAsteroid(

    @PrimaryKey
    val id: Long,

    val codename: String,
    @ColumnInfo(name = "close_approach_date")
    val closeApproachDate: String,

    @ColumnInfo(name = "relative_velocity")
    val relativeVelocity: String,

    @ColumnInfo(name = "miss_distance")
    val missDistance: String,

    @ColumnInfo(name = "absolute_magnitude")
    val absoluteMagnitude: String,

    @ColumnInfo(name = "estimated_diameter")
    val estimatedDiameter:String,

    @ColumnInfo(name = "is_potentially_hazardous_asteroid")
    val isPotentiallyHazardous: Boolean
) : Parcelable