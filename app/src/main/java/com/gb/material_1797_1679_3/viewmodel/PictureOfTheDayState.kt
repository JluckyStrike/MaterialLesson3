package com.gb.material_1797_1679_3.viewmodel

import com.gb.material_1797_1679_3.repository.PictureOfTheDayResponseData

sealed class PictureOfTheDayState {
    data class Success(val serverResponseData: PictureOfTheDayResponseData) : PictureOfTheDayState()
    data class Error(val error: Throwable) : PictureOfTheDayState()
    data class Loading(val progress: Int?) : PictureOfTheDayState()
}