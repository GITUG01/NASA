package com.gitug01.nasa.domain

import android.app.Application
import android.content.Context
import com.gitug01.nasa.data.retrofit.RetrofitNasaApiImpl

class App : Application() {
    val imageRepo: ImageRepo by lazy { RetrofitNasaApiImpl() }
}

val Context.app
    get() = applicationContext as App