package com.dev.ccodetest.screens.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.ccodetest.base.BaseUTTest
import com.dev.ccodetest.di.configureTestAppComponent
import com.hegazy.ebtikar.repo.repo.PopularPeoplesRepo
import com.hegazy.ebtikar.viewmodel.PopularPeoplesViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class LoginActivityViewModelTest : BaseUTTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mPopActivityViewModel: PopularPeoplesViewModel
    val mDispatcher = Dispatchers.Unconfined

    @MockK
    lateinit var mPopularUseCase: PopularPeoplesRepo

    val mParam = 1

    @Before
    fun start() {
        super.setUp()
        //Used for initiation of Mockk
        MockKAnnotations.init(this)
        //Start Koin with required dependencies
        startKoin {
            modules(configureTestAppComponent(getMockWebServerUrl()))
        }
    }

    @Test
    fun test_login_view_model_data_populates_expected_value() {

        mPopActivityViewModel = PopularPeoplesViewModel(mPopularUseCase)
//        val sampleResponse = getJson("success_resp_popular_peoples.json")
//        var jsonObj = Gson().fromJson(sampleResponse, PeopleResponse.Result::class.java)
        //Make sure login use case returns expected response when called
//        coEvery { mLoginUseCase.getPopularPeoples1(any()) } returns jsonObj
//        mLoginActivityViewModel.extractedPeoples.observeForever {  }

//        mLoginActivityViewModel.getPopularPeoples(mParam)

        assert(mPopActivityViewModel.extractedPeoples.value != null)
//        assert(
//            mLoginActivityViewModel.mAllPeopleResponse.value!!.responseStatus
//                == LiveDataWrapper.RESPONSESTATUS.SUCCESS)
//        val testResult = mLoginActivityViewModel.mAllPeopleResponse.value as LiveDataWrapper<AllPeople>
//        assertEquals(testResult.response!!.next,mNextValue)
//

    }
}