package com.example.kotlinlesson2.repository

import com.example.kotlinlesson2.domain.Weather

class RepositoryImpl:Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalSource(): Weather {
        return Weather()
    }
}