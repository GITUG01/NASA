package com.gitug01.nasa.domain.entity

data class MarsWeatherMainEntity(
    val AT: MarsWeatherEntity,
    val First_UTC: String,
    val HWS: MarsWeatherEntity
)
