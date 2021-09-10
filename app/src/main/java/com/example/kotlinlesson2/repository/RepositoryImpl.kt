package com.example.kotlinlesson2.repository

import com.example.kotlinlesson2.domain.Weather
import com.example.kotlinlesson2.domain.getRussianCities
import com.example.kotlinlesson2.domain.getWorldCities

class RepositoryImpl:Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }
}