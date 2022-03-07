package com.gb.material_1797_1679_3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.material_1797_1679_3.BuildConfig
import com.gb.material_1797_1679_3.repository.MarsData
import com.gb.material_1797_1679_3.repository.PictureOfTheDayResponseData
import com.gb.material_1797_1679_3.repository.PictureOfTheDayRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayState> = MutableLiveData(),
    private val liveData2: MutableLiveData<PictureOfTheDayMarsState> = MutableLiveData(),
    private val pictureOfTheDayRetrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
):ViewModel() {

    fun getLiveData():LiveData<PictureOfTheDayState>{
        return  liveData
    }


    fun getLiveData2():LiveData<PictureOfTheDayMarsState>{
        return  liveData2
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

    fun sendServerRequest2(){
        liveData2.postValue(PictureOfTheDayMarsState.Loading(null))
        pictureOfTheDayRetrofitImpl.getRetrofitImpl().getPictureOfTheMarsDay(BuildConfig.NASA_API_KEY).enqueue(
            object : Callback<MarsData>{
                override fun onResponse(call: Call<MarsData>, response: Response<MarsData>) {
                    if (response.isSuccessful && response.body() != null){
                        response.body()?.let {
                            liveData2.postValue(PictureOfTheDayMarsState.Success(it))
                        }
                    }else{

                    }
                }

                override fun onFailure(call: Call<MarsData>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            }
        )

        /*liveData.postValue(PictureOfTheDayMarsState.Loading(null))
        pictureOfTheDayRetrofitImpl.getRetrofitImpl().getPictureOfTheMarsDay(BuildConfig.NASA_API_KEY).enqueue(
            object : Callback<MarsData>{
                override fun onResponse(call: Call<MarsData>, response: Response<MarsData>) {
                    if (response.isSuccessful && response.body() != null){
                        response.body()?.let {
                            liveData.postValue(PictureOfTheDayMarsState.Success(it))
                        }
                    }else{

                    }
                }

                override fun onFailure(call: Call<MarsData>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            }
        )*/
    }
}


