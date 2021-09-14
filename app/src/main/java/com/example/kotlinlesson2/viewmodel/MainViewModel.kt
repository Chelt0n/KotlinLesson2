package com.example.kotlinlesson2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinlesson2.repository.RepositoryImpl


class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(false)


    fun getWeatherFromLocalSourceRussia() = getDataFromLocalSource(true)

    private fun getDataFromLocalSource(isRussian: Boolean) {

        with(liveDataToObserve) {
            postValue(Loading)
            Thread {

                if (isRussian)
                    postValue(Success(repositoryImpl.getWeatherFromLocalStorageRus()))
                else
                    postValue(Success(repositoryImpl.getWeatherFromLocalStorageWorld()))
            }.start()
        }
    }


}
