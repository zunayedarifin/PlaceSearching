package com.arifin.placesearhing.network

import com.arifin.placesearhing.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api private constructor(baseUrl: String) {
    private val mRetrofit: Retrofit
    private fun <T> createService(serviceClass: Class<T>): T {
        return mRetrofit.create(serviceClass)
    }

    companion object {
        private var nearByPlaceSearch: ApiService? = null
        val orkoApiService: ApiService
            get() {
                if (nearByPlaceSearch == null) {
                    nearByPlaceSearch = getApiService(BuildConfig.API_BASE_URL)
                }
                return nearByPlaceSearch!!
            }

        private fun getApiService(baseUrl: String): ApiService {
            return Api(baseUrl).createService(ApiService::class.java)
        }
    }

    init {

        /* Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().connectTimeout(
            120,
            java.util.concurrent.TimeUnit.SECONDS
        ).readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build()
        mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}