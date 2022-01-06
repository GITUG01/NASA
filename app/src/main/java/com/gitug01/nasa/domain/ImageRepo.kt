package com.gitug01.nasa.domain

import androidx.annotation.WorkerThread
import com.gitug01.nasa.domain.entity.ImageEntity
import com.gitug01.nasa.domain.entity.MarsWeatherEntity
import com.gitug01.nasa.domain.entity.MarsWeatherMainEntity

interface ImageRepo {
    @WorkerThread
    suspend fun getImageOfTheDayAsync(apiKey: String): ImageEntity
    suspend fun getMarsImage(sol: String, apiKey: String): String
    suspend fun getMarsWeather(apiKey: String, feedtype: String, version: String): MarsWeatherMainEntity
}
