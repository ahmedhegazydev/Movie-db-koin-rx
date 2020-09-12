package com.hegazy.ebtikar.repo

import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import timber.log.Timber

class DetailsPeopleRepo : BaseRepository() {

    suspend fun getPeopleImages(peopleId: Int?): Any {
        return callApi(call = {
            Timber.d("pageIndex = " + peopleId)
            val serviceAPI: NetworkModule = NetworkModule()
            val service: ApiEndpointInterface = serviceAPI.retrofit()
            service.getPeopleImages(peopleId).await()
        })
    }


}