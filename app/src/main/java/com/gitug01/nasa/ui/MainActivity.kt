package com.gitug01.nasa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gitug01.nasa.R
import com.gitug01.nasa.domain.ImageRepo
import com.gitug01.nasa.domain.app
import com.gitug01.nasa.domain.entity.ImageEntity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val imageRepo: ImageRepo by lazy { app.imageRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageEtity: ImageEntity? = null

        CoroutineScope(Dispatchers.IO).launch {
            val a = imageRepo.getImageOfTheDayAsync("EMV8EipQtIhSIQACkadTQOSSC6oES7H5cfM6xTjZ")
            withContext(Dispatchers.Main){
                imageEtity = a
            }
        }


    }
}