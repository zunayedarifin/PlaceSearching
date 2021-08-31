package com.arifin.placesearhing.network

import com.arifin.placesearhing.model.nearbyplaces.NearByPlace
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {

    @Headers("Accept: application/json")
    @GET("place/nearbysearch/json?")
    fun getNearByPlace(
        @Query("location") location: String?,
        @Query("radius") radius: String?,
        // @Query("types") types: String?,
        @Query("name") name: String?,
        @Query("key") key: String?
    ): Call<NearByPlace>
}