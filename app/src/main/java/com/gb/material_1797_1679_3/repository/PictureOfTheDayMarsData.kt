package com.gb.material_1797_1679_3.repository

import com.google.gson.annotations.SerializedName

data class PictureOfTheDayMarsData(
    val id: Long,
    val sol: Long,
    @SerializedName("mediaType")
    val imgSrc: String,
)
