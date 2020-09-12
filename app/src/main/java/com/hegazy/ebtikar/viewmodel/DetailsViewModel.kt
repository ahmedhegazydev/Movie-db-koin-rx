package com.hegazy.ebtikar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.repo.APICallResult
import com.hegazy.ebtikar.repo.DetailsPeopleRepo
import com.hegazy.ebtikar.repo.toErrorMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel(private val repo: DetailsPeopleRepo) : ViewModel() {

    private var page = 1
    private val size = 10
    private val hasNextPage: MutableLiveData<Boolean> = MutableLiveData()
    internal val isRequesting = MutableLiveData<Boolean>()
    internal val extractedPeoples = MutableLiveData<MutableList<PeopleResponse.Result>>()
    internal val errorSingleLiveEvent = MutableLiveData<Int>()
    private var myJob: Job? = null


    fun getPopularPeoples(pageIndex: Int = page) {
        Timber.d("getPopularPeoples: ")
        page = pageIndex
        myJob = viewModelScope.launch {
            when (val response = repo.getPopularPeoples(pageIndex)) {
                is APICallResult.Success<*> -> {
                    Timber.d("response success")
                    isRequesting.postValue(false)
                    extractedPeoples.postValue((response.data as PeopleResponse?)?.results)
                }

                is APICallResult.Error<*> -> {
                    Timber.d("response error")
                    isRequesting.postValue(false)
                    errorSingleLiveEvent.postValue(response.toErrorMessage())
                }
            }
        }
    }

    fun findNextPopularPeople() {
        getPopularPeoples(page + 1)
    }


    override fun onCleared() {
        super.onCleared()

        myJob?.cancel()

    }

}