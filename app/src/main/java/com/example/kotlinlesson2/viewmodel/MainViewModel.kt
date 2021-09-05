package com.example.kotlinlesson2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinlesson2.domain.Weather
import com.example.kotlinlesson2.repository.RepositoryImpl
import java.lang.IllegalStateException
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = liveDataToObserve
    fun getDataFromRemoteSource() {
        liveDataToObserve.postValue(AppState.Loading)
        val random = (0..10).random()
        Thread {
            sleep(3000)

            if (random > 5)
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromRemoteSource()))
            else
                liveDataToObserve.postValue(AppState.Error(IllegalStateException()))
        }.start()
    }
}
