package com.gitug01.nasa.data.retrofit

import com.gitug01.nasa.domain.entity.ImageEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("planetary/apod")
    fun getImageOfTheDay(
        @Query("api_key") apiKey: String
    ): Call<ImageEntity>

}