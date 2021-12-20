package com.gitug01.nasa.domain

import androidx.annotation.WorkerThread
import com.gitug01.nasa.domain.entity.ImageEntity

interface ImageRepo {
    @WorkerThread
    suspend fun getImageOfTheDayAsync(apiKey: String): ImageEntity
}
