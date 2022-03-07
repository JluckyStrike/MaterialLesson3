package com.gb.material_1797_1679_3.repository


import com.google.gson.annotations.SerializedName

data class LatestPhoto(
    val camera: Camera,
    @SerializedName("earthDate")
    val earthDate: String,
    val id: Int,
    @SerializedName("imgSrc")
    val imgSrc: String,
    val rover: Rover,
    val sol: Int
)