package com.hegazy.ebtikar.repo.remote.retrofit.api

import com.hegazy.ebtikar.model.PeopleResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpointInterface {


    @GET("person/popular?api_key=242d544fe443aa59e56d47a3d5f2d6c4&language=en-US")
    fun getPopularPeoples(
        @Query("PageIndex") PageIndex: Int,
        @Query("PageSize") PageSize: Int
    ): Deferred<Response<PeopleResponse>>


}
