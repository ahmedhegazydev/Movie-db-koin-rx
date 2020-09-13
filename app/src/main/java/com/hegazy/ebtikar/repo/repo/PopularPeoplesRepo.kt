package com.hegazy.ebtikar.repo.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.repo.BaseRepository
import com.hegazy.ebtikar.repo.NetworkModule
import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import com.hegazy.ebtikar.utils.AbstractPagingSource
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class PopularPeoplesRepo : BaseRepository(), KoinComponent {
//class PopularPeoplesRepo(private val service: ApiEndpointInterface) : BaseRepository() {

    private val serviceAPI: NetworkModule by inject()
    private val service: ApiEndpointInterface = serviceAPI.retrofit()

//    suspend fun getPopularPeoples(pageIndex: Int): Any {
//        return callApi(call = {
//            Timber.d("pageIndex = " + pageIndex)
//            service.getPopularPeoples(pageIndex).await()
//        })
//    }


    var cashedDate: String = ""

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    fun getPopularPeoples(): Flow<PagingData<PeopleResponse.Result>> {
//        Timber.d("here")
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                object : AbstractPagingSource<PeopleResponse.Result>() {
                    override suspend fun fetchData(
                        PageIndex: Int,
                        PageSize: Int
                    ): List<PeopleResponse.Result> {
                        val response = service.getPopularPeoples(PageIndex).await()
                        return response.body()?.results!!
                    }

                }.getPagingSource()
            }
        ).flow
    }


}