package com.hegazy.ebtikar.repo

import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiManager

class PopularPeoplesRepo : BaseRepository() {

    suspend fun getPopularPeoples(pageIndex: Int, pageSize: Int): Any {
        return callApi(call = {
            ApiManager().networkInterface.getPopularPeoples(pageIndex, pageSize).await()
        })
    }
}