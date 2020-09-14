package com.hegazy.ebtikar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.repo.APICallResult
import com.hegazy.ebtikar.repo.repo.PopularPeoplesRepo
import com.hegazy.ebtikar.repo.toErrorMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class PopularPeoplesViewModel(private val repo: PopularPeoplesRepo) : ViewModel() {

    private var page = 1
    private val size = 10
    val hasNextPage: MutableLiveData<Boolean> = MutableLiveData()
    internal val isRequesting = MutableLiveData<Boolean>()
    val extractedPeoples = MutableLiveData<MutableList<PeopleResponse.Result>>()
    private var myJob: Job? = null


    //    var items: PagingData<PeopleResponse.Result>? = null
//    var items: PagingData<PeopleResponse>? = null
    internal val successMutableLiveData = MutableLiveData<PagingData<PeopleResponse.Result>>()
    internal val errorSingleLiveEvent = MutableLiveData<Int>()
    val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getPopularPeoples(pageIndex: Int = page) {
        Timber.d("getPopularPeoples: ")
        page = pageIndex
        myJob = viewModelScope.launch {
            when (val response = repo.getPopularPeoples1(pageIndex)) {
                is APICallResult.Success<*> -> {
                    val resp = response.data as PeopleResponse?
                    Timber.d("response success")
                    isRequesting.postValue(false)
                    extractedPeoples.postValue((resp)?.results)
                    if (resp!!.page < resp.total_pages) {
//                        Timber.d("less than ")
                        hasNextPage.postValue(true)
                    } else {
                        hasNextPage.postValue(false)
                    }
                }
                is APICallResult.Error<*> -> {
                    Timber.d("response error")
                    isRequesting.postValue(false)
                    errorSingleLiveEvent.postValue(response.toErrorMessage())
                }
            }
        }
    }


//    fun getPopularPeoples(): Flow<PagingData<PeopleResponse>> {
//        _dataLoading.value = true
//        return repo.getPopularPeoples().cachedIn(viewModelScope)
//    }

    fun findNextPopularPeople() {
        getPopularPeoples(page + 1)
    }


    override fun onCleared() {
        super.onCleared()
        myJob?.cancel()
    }

}