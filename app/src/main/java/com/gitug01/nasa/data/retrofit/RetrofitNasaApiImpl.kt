package com.gitug01.nasa.data.retrofit

import com.gitug01.nasa.domain.ImageRepo
import com.gitug01.nasa.domain.entity.ImageEntity
import com.gitug01.nasa.domain.entity.MarsWeatherEntity
import com.gitug01.nasa.domain.entity.MarsWeatherMainEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.nasa.gov")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private var api: NasaApi = retrofit.create(NasaApi::class.java)

class RetrofitNasaApiImpl : ImageRepo {
    override suspend fun getImageOfTheDayAsync(apiKey: String): ImageEntity {

        return CoroutineScope(Dispatchers.IO).async {
            val result = api.getImageOfTheDay(apiKey)
                .execute()
                .body()

            val copyright = result?.copyright ?: "No name"
            val date = result?.date ?: ""
            val url = result?.url ?: ""
            val title = result?.title ?: ""
            var explanation = result?.explanation ?: ""

            val resultImageEntity = ImageEntity(copyright, date, url, title, explanation)
            return@async resultImageEntity
        }.await()
    }

    override suspend fun getMarsImage(sol: String, apiKey: String): String {
        return CoroutineScope(Dispatchers.IO).async {
            val result = api.getMarsImage(sol, apiKey)
            val a = result.execute()
            val b = a.body()

            val resultPhoto = b?.photos ?: ""
            return@async resultPhoto
        }.await()
    }

    override suspend fun getMarsWeather(apiKey: String, feedtype: String, version: String): MarsWeatherMainEntity {
        return (CoroutineScope(Dispatchers.IO).async {
            val result = api.getMarsWeather(apiKey, feedtype, version)
            val a = result.execute()
            val b = a.body()
            val c = b!!

            val bbb = c.aA

            val first = bbb.AT
            val second = bbb.First_UTC
            val third = bbb.HWS
            val fourth = bbb.PRE
            return@async MarsWeatherMainEntity(first, second, third, fourth)
        }.await())
    }
}