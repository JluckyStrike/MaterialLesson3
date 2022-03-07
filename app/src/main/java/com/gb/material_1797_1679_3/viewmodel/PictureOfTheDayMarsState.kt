package com.gb.material_1797_1679_3.viewmodel

import com.gb.material_1797_1679_3.repository.MarsData

sealed class PictureOfTheDayMarsState{
    data class Success(val serverResponseData: MarsData) : PictureOfTheDayMarsState()
    data class Error(val error: Throwable) : PictureOfTheDayMarsState()
    data class Loading(val progress: Int?) : PictureOfTheDayMarsState()
}
