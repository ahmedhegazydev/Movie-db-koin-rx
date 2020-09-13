package com.hegazy.ebtikar.utils

import androidx.paging.PagingSource
import timber.log.Timber


private const val STARTING_PAGE_INDEX = 1

abstract class AbstractPagingSource<U : Any> {

    fun getPagingSource(): PagingSource<Int, U> {
        return object : PagingSource<Int, U>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, U> {
                val position = params.key ?: STARTING_PAGE_INDEX
                return try {
                    val response = fetchData(position, 20)
//                    val response = fetchData(position)
                    Timber.d("here")
                    LoadResult.Page(
                        data = response,
                        prevKey = if (position == STARTING_PAGE_INDEX) null
                        else position - 1,
                        nextKey = if (response.isEmpty()) null
                        else position + 1
                    )

                } catch (exception: Throwable) {
//                    Timber.d("here")
                    LoadResult.Error<Int, U>(exception)
                }
            }
        }
    }

    abstract suspend fun fetchData(PageIndex: Int, PageSize: Int): List<U>
//    abstract suspend fun fetchData(PageIndex: Int): List<U>
}
