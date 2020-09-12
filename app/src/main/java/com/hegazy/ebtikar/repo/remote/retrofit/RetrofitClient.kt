package com.hegazy.ebtikar.repo.remote.retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private val BASE_URL = NetworkConstants.getBaseUrl()
    private var retrofit: Retrofit? = null

    // use singleton design pattern to create single instance of RetrofitClient for the app
    val instance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }


}