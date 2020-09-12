package com.hegazy.ebtikar.repo.remote.retrofit

import com.hegazy.ebtikar.BuildConfig


object NetworkConstants {

    const val BASE_IMAGE_PATH = "https://image.tmdb.org/t/p/w780//"

    //Success
    const val successWithoutBody = 2

    //Error
    //Exceptions
    const val unknownHostException = 3
    const val socketTimeoutException = 4
    const val sslHandshakeException = 5
    const val genericException = 6

    //HTTP codes
    const val clientSideError = 7
    const val serverSideError = 8
    const val forbidden = 9

    //Generic
    const val genericError = 10

    //Internet connection variants

    //Connected
    const val wifi = 0
    const val cellular = 1

    //Disconnected
    const val disconnected = 2

    //Generic
    const val generic = 3


    //Base urls
    private val QA: Int = BuildConfig.QA
    private const val BASE_URL_PRODUCATION = "http://api.themoviedb.org/3/"
    private const val BASE_URL_DEVELOPMENT = "http://api.themoviedb.org/3/"
    private const val BASE_URL_STAGING = "http://api.themoviedb.org/3/"

    fun getBaseUrl(): String {
        return when (QA) {
            FlavorType.DEVELOPING.value -> BASE_URL_DEVELOPMENT
            FlavorType.STAGING.value -> BASE_URL_STAGING
            FlavorType.PRODUCTION.value -> BASE_URL_PRODUCATION
            else -> BASE_URL_DEVELOPMENT
        }

    }
}