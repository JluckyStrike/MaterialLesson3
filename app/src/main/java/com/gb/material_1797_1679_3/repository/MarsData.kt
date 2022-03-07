package com.gb.material_1797_1679_3.repository


import com.google.gson.annotations.SerializedName

data class MarsData(
    @SerializedName("latest_photos")
    val latestPhotos: List<LatestPhoto>,
    @SerializedName("imgSrc")
    val imgSrc: String
)