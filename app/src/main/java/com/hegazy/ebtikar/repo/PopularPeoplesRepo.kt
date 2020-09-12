package com.hegazy.ebtikar.repo

import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import timber.log.Timber

class PopularPeoplesRepo : BaseRepository() {

    suspend fun getPopularPeoples(pageIndex: Int): Any {
        return callApi(call = {
            Timber.d("pageIndex = " + pageIndex)
            //Injected GithubServiceAPI
            val serviceAPI: NetworkModule = NetworkModule()
            val service: ApiEndpointInterface = serviceAPI.retrofit()
            service.getPopularPeoples(pageIndex).await()

        })
    }


}