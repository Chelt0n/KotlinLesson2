package com.example.kotlinlesson2.view

import com.example.kotlinlesson2.domain.Weather

interface OnItemViewClickListener {
    fun onItemClick(weather: Weather)
}