package com.gb.material_1797_1679_3.viewmodel

import com.gb.material_1797_1679_3.repository.MarsPhotosServerResponseData
import com.gb.material_1797_1679_3.repository.PictureOfTheDayResponseData
import com.gb.material_1797_1679_3.repository.SolarFlareResponseData

sealed class PictureOfTheDayState {
    data class Success(val serverResponseData: PictureOfTheDayResponseData) : PictureOfTheDayState()
    data class SuccessMars(val serverResponseData: MarsPhotosServerResponseData) : PictureOfTheDayState()
    data class Error(val error: Throwable) : PictureOfTheDayState()
    data class SuccessWeather(val solarFlareResponseData:List<SolarFlareResponseData>) : PictureOfTheDayState()
    //data class Loading(val progress: Int?) : PictureOfTheDayState()
    object Loading : PictureOfTheDayState()

}