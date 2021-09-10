package com.example.kotlinlesson2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinlesson2.repository.RepositoryImpl


class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceWorld() {
        getDataFromLocalSource(false)
    }

    fun getWeatherFromLocalSourceRussia() {
        getDataFromLocalSource(true)
    }

    private fun getDataFromLocalSource(isRussian: Boolean) {

        liveDataToObserve.postValue(AppState.Loading)
        Thread {

            if (isRussian)
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorageRus()))
            else
                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorageWorld()))
        }.start()
    }

}
