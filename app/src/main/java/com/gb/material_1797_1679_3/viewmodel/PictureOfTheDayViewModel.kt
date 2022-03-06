package com.gb.material_1797_1679_3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.material_1797_1679_3.BuildConfig
import com.gb.material_1797_1679_3.repository.PictureOfTheDayResponseData
import com.gb.material_1797_1679_3.repository.PictureOfTheDayRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayState> = MutableLiveData(),
    private val pictureOfTheDayRetrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
):ViewModel() {

    fun getLiveData():LiveData<PictureOfTheDayState>{
        return  liveData
    }

    fun sendServerRequest(){
        liveData.postValue(PictureOfTheDayState.Loading(null))
        pictureOfTheDayRetrofitImpl.getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(
            object : Callback<PictureOfTheDayResponseData>{
                override fun onResponse(
                    call: Call<PictureOfTheDayResponseData>,
                    response: Response<PictureOfTheDayResponseData>
                ) {
                    if(response.isSuccessful&&response.body()!=null){
                        response.body()?.let {
                            liveData.postValue(PictureOfTheDayState.Success(it))
                        }
                    }else{
                        //TODO HW
                    }
                }

                override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
                    //TODO HW
                }

            }
        )
    }

}