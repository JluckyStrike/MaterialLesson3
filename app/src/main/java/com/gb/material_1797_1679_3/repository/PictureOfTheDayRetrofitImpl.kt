package com.gb.material_1797_1679_3.repository

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PictureOfTheDayRetrofitImpl {
    private val baseUrl = "https://api.nasa.gov/"
    fun getRetrofitImpl():PictureOfTheDayAPI{
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return retrofit.create(PictureOfTheDayAPI::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
    }

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(PictureOfTheDayAPI::class.java)
    }

    fun getMarsPictureByDate(earth_date: String, apiKey: String, marsCallbackByDate: Callback<MarsPhotosServerResponseData>) {
        api.getMarsImageByDate(earth_date, apiKey).enqueue(marsCallbackByDate)
    }

    fun getSolarFlare(apiKey: String, podCallback: Callback<List<SolarFlareResponseData>>, startDate:String="2021-09-07") {
        api.getSolarFlare(apiKey,startDate).enqueue(podCallback)
    }
}