package com.hegazy.ebtikar.repo.remote.retrofit.api

import com.hegazy.ebtikar.model.DetailsResponse
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.repo.remote.retrofit.ApiUrls
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpointInterface {


    @GET(ApiUrls.GET_ALL_POPULAR_PEOPLE)
    fun getPopularPeoples(
        @Query("page") PageIndex: Int
    ): Deferred<Response<PeopleResponse>>

    @GET(ApiUrls.GET_PEOPLE_IMAGES)
    fun getPeopleImages(
        @Path("person_id") personId: Int
    ): Deferred<Response<DetailsResponse>>


}
