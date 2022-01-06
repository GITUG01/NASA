package com.gitug01.nasa.data.retrofit

import com.gitug01.nasa.domain.entity.ImageEntity
import com.gitug01.nasa.domain.entity.MarsWeatherEntity
import com.gitug01.nasa.domain.entity.MarsWeatherMainEntity
import com.gitug01.nasa.domain.entity.O
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("planetary/apod")
    fun getImageOfTheDay(
        @Query("api_key") apiKey: String
    ): Call<ImageEntity>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsImage(
        @Query("sol") sol: String,
        @Query("api_key") apiKey: String
    ): Call<MarsImage>

    @GET("insight_weather/")
    fun getMarsWeather(
        @Query("api_key") apiKey: String,
        @Query("feedtype") feedtype: String,
        @Query("ver") version: String
    ): Call<O>

}