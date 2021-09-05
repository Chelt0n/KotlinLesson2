package com.example.kotlinlesson2.domain

data class Weather(
    val city: City = City("Москва", 55.37, 37.00),
    val temperature: Int = -1,
    val feelsLike: Int = -5
)