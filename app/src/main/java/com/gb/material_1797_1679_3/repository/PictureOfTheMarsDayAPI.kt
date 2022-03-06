package com.gb.material_1797_1679_3.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheMarsDayAPI {
    @GET("mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&")
    fun getPictureOfTheMarsDay(@Query("api_key") apiKey: String): Call<PictureOfTheDayResponseData>
}