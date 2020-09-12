package com.hegazy.ebtikar.repo.remote.retrofit.api

import org.koin.core.KoinComponent
import org.koin.core.inject


class ApiManager : KoinComponent {


    val networkInterface: ApiEndpointInterface by inject()

//    init {
//        init()
//    }
//
//    private fun init() {
//        networkInterface = networkComponent.getApiManager()
//    }

}