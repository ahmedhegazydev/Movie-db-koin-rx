package com.hegazy.ebtikar.repo.repo

import com.hegazy.ebtikar.repo.BaseRepository
import com.hegazy.ebtikar.repo.NetworkModule
import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class PopularPeoplesRepo : BaseRepository(), KoinComponent {

    private val serviceAPI: NetworkModule by inject()
    private val service: ApiEndpointInterface = serviceAPI.retrofit()

    suspend fun getPopularPeoples1(pageIndex: Int): Any {
        return callApi(call = {
            Timber.d("pageIndex = %s", pageIndex)
            service.getPopularPeoples(pageIndex).await()
        })
    }

}