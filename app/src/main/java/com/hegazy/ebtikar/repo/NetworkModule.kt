package com.hegazy.ebtikar.repo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants
import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {


    fun networkInterface(retrofit: Retrofit): ApiEndpointInterface {
        return retrofit.create(ApiEndpointInterface::class.java)
    }

    fun retrofit(
    ): ApiEndpointInterface {

        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(NetworkConstants.getBaseUrl())
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .client(okHttpClient)
        val retrofit: Retrofit = retrofitBuilder.build()
        return retrofit.create(ApiEndpointInterface::class.java)
    }


    fun gson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }


    fun okHttpClient(
        dispatcher: Dispatcher
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .build()
    }


}