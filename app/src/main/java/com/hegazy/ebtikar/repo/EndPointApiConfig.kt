package com.hegazy.ebtikar.repo

import com.google.gson.Gson
import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import okhttp3.Dispatcher

open class EndPointApiConfig(
    dispatcher: Dispatcher,
    gson: Gson
) : AppAPIConfig(dispatcher, gson) {

    fun <T> getApiService(): Class<T> {
        return ApiEndpointInterface::class.java as Class<T>
    }
}