package com.arifin.placesearhing.repository

import androidx.lifecycle.MutableLiveData
import com.arifin.placesearhing.model.nearbyplaces.NearByPlace
import com.arifin.placesearhing.network.Api
import com.arifin.placesearhing.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NearByPlacesRepository {
    private val isUpdated: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    fun getIsUpdated(): MutableLiveData<Boolean> {
        return isUpdated
    }

    fun placeList(
        location: String,
        radius: String,
        name: String,
        key: String
    ): MutableLiveData<NearByPlace> {
        val nearByPlaceList: MutableLiveData<NearByPlace> = MutableLiveData<NearByPlace>()
        isUpdated.value = false
        val apiReader: ApiService = Api.orkoApiService
        val list: Call<NearByPlace> = apiReader.getNearByPlace(location, radius, name, key)
        list.enqueue(object : Callback<NearByPlace?> {
            override fun onResponse(
                call: Call<NearByPlace?>,
                response: Response<NearByPlace?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        nearByPlaceList.postValue(it)
                        isUpdated.value = true
                    }
                }
            }

            override fun onFailure(call: Call<NearByPlace?>, t: Throwable) {
                isUpdated.value = false
            }
        })
        return nearByPlaceList
    }

}