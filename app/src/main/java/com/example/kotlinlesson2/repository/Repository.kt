package com.example.kotlinlesson2.repository

import com.example.kotlinlesson2.domain.Weather

interface Repository {
    fun getWeatherFromRemoteSource():Weather
    fun getWeatherFromLocalSource():Weather
}