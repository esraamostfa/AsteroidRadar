package com.esraa.egfwd.asteroidradar

import com.esraa.egfwd.asteroidradar.utils.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//get date of today
val formatter = DateTimeFormatter.ofPattern(Constants.API_QUERY_DATE_FORMAT)
val todayDate = LocalDate.now()
val today = todayDate.format(formatter).toString()


val weekDates : List<LocalDate> = listOf(
    todayDate,
    todayDate.plusDays(1),
    todayDate.plusDays(2),
    todayDate.plusDays(3),
    todayDate.plusDays(4),
    todayDate.plusDays(5),
    todayDate.plusDays(6),
)
val weekDatesString = weekDates.map {
    it.format(formatter).toString()
}