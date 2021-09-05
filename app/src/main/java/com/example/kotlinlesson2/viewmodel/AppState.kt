package com.example.kotlinlesson2.viewmodel

import com.example.kotlinlesson2.domain.Weather

sealed class AppState {
    object Loading : AppState()
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()

}