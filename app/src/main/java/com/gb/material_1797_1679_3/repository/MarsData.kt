package com.gb.material_1797_1679_3.repository


import com.google.gson.annotations.SerializedName

data class MarsData(
    @SerializedName("earthDate")
    val earthDate: String,
    @SerializedName("imgSrc")
    val imgSrc: String
)