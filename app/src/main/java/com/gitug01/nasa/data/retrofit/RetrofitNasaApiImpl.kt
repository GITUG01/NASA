package com.gitug01.nasa.data.retrofit

import com.gitug01.nasa.domain.ImageRepo
import com.gitug01.nasa.domain.entity.ImageEntity
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
}