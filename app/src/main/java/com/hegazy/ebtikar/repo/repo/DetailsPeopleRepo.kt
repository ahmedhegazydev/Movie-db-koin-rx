package com.hegazy.ebtikar.repo.repo

import com.hegazy.ebtikar.repo.BaseRepository
import com.hegazy.ebtikar.repo.NetworkModule
import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class DetailsPeopleRepo : BaseRepository(), KoinComponent {

    suspend fun getPeopleImages(peopleId: Int?): Any {
        val serviceAPI: NetworkModule by inject()
        val service: ApiEndpointInterface = serviceAPI.retrofit()
        return callApi(call = {
            Timber.d("peopleId = " + peopleId)
            service.getPeopleImages(peopleId).await()
        })
    }


}