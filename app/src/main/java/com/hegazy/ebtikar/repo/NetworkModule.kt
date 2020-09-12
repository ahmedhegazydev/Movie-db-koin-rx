package com.hegazy.ebtikar.repo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants
import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {


    fun retrofit(
    ): ApiEndpointInterface {

        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(NetworkConstants.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
        val retrofit: Retrofit = retrofitBuilder.build()
        return retrofit.create(ApiEndpointInterface::class.java)
    }


    fun gson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }



}