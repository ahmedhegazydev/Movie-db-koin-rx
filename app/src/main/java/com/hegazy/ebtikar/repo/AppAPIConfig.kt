package com.hegazy.ebtikar.repo

import com.google.gson.Gson
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants
import okhttp3.Dispatcher

open class AppAPIConfig(
    val dispatcher: Dispatcher,
    val gson: Gson
) {


    companion object {
        val BASE_URL = NetworkConstants.getBaseUrl()
    }

//    override fun addOKHttpConfig(client: OkHttpClient.Builder) {
//
//        client.connectTimeout(100, TimeUnit.SECONDS)
//            .writeTimeout(100, TimeUnit.SECONDS)
//            .readTimeout(100, TimeUnit.SECONDS)
//            .cache(null)
//            .retryOnConnectionFailure(true)
//            .dispatcher(dispatcher)
//    }
//
//    override fun addRetrofitConfig(retrofitBuilder: Retrofit.Builder) {
//
//        retrofitBuilder
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//
//    }


}