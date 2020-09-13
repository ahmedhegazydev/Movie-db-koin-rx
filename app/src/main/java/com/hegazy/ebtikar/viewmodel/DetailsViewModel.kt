package com.hegazy.ebtikar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegazy.ebtikar.model.DetailsResponse
import com.hegazy.ebtikar.repo.APICallResult
import com.hegazy.ebtikar.repo.repo.DetailsPeopleRepo
import com.hegazy.ebtikar.repo.toErrorMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel(private val repo: DetailsPeopleRepo) : ViewModel() {

    internal val isRequesting = MutableLiveData<Boolean>()
    internal val extractedImages = MutableLiveData<MutableList<DetailsResponse.Profile>>()
    internal val errorSingleLiveEvent = MutableLiveData<Int>()
    private var myJob: Job? = null

    fun getPeopleImages(peopleId: Int?) {
        Timber.d("getPeopleImages: ")
        myJob = viewModelScope.launch {
            when (val response = repo.getPeopleImages(peopleId)) {
                is APICallResult.Success<*> -> {
                    Timber.d("response success")
                    isRequesting.postValue(false)
                    extractedImages.postValue((response.data as DetailsResponse?)?.profiles)
                }

                is APICallResult.Error<*> -> {
                    Timber.d("response error")
                    isRequesting.postValue(false)
                    errorSingleLiveEvent.postValue(response.toErrorMessage())
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()

        myJob?.cancel()

    }

}