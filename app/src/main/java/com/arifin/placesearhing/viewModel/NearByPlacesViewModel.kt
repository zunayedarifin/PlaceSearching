package com.arifin.placesearhing.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arifin.placesearhing.model.nearbyplaces.NearByPlace
import com.arifin.placesearhing.repository.NearByPlacesRepository

class NearByPlacesViewModel : ViewModel() {
    private var nearByPlacesRepository: NearByPlacesRepository = NearByPlacesRepository()
    fun getNearByPlaces(
        location: String,
        radius: String,
        types: String,
        name: String,
        key: String
    ): MutableLiveData<NearByPlace> {
        return nearByPlacesRepository.placeList(location, radius, types, name, key)
    }

    fun getIsUpdate(): MutableLiveData<Boolean> {
        return nearByPlacesRepository.getIsUpdated()
    }

}