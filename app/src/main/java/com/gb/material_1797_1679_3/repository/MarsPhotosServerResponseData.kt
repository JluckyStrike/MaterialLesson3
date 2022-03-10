package com.gb.material_1797_1679_3.repository

import com.google.gson.annotations.SerializedName

data class MarsPhotosServerResponseData(
    @SerializedName("photos") val photos: ArrayList<MarsData>,
)
