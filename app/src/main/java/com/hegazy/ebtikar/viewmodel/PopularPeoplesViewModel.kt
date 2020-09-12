package com.hegazy.ebtikar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.repo.APICallResult
import com.hegazy.ebtikar.repo.PopularPeoplesRepo
import com.hegazy.ebtikar.repo.toErrorMessage
import kotlinx.coroutines.launch
import timber.log.Timber

class PopularPeoplesViewModel : ViewModel() {

    private var page = 1
    private val size = 10
    val hasNextPage: MutableLiveData<Boolean> = MutableLiveData()

    val isRequesting = MutableLiveData<Boolean>()
    private var repo = PopularPeoplesRepo()

    internal val extractedPoeples = MutableLiveData<MutableList<PeopleResponse.Result>>()
    internal val errorSingleLiveEvent = MutableLiveData<Int>()


    fun getPopularPeoples(pageIndex: Int = page) {
        Timber.d("getPopularPeoples: ")
        page = pageIndex
        viewModelScope.launch {
            when (val response = repo.getPopularPeoples(pageIndex)) {
                is APICallResult.Success<*> -> {
                    Timber.d("response success")
                    isRequesting.postValue(false)
                    extractedPoeples.postValue((response.data as PeopleResponse?)?.results)

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


}