package com.dev.ccodetest.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.ccodetest.base.BaseUTTest
import com.dev.ccodetest.di.configureTestAppComponent
import com.hegazy.ebtikar.repo.remote.retrofit.api.ApiEndpointInterface
import com.hegazy.ebtikar.repo.repo.PopularPeoplesRepo
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.mockito.Mockito.mock
import java.io.IOException
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class LoginRepositoryTest : BaseUTTest() {

    var context: Context = mock(Context::class.java)

    //Target
    private lateinit var mRepo: PopularPeoplesRepo

    //Inject api service created with koin
    val mAPIService: ApiEndpointInterface by inject()

    //Inject Mockwebserver created with koin
    val mockWebServer: MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mParam = 1
    val mCount = 87

    @Before
    fun start() {
        super.setUp()

        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun test_popular_repo_retrieves_expected_data() = runBlocking<Unit> {

        mockNetworkResponseWithFileContent(
            "success_resp_popular_peoples.json",
            HttpURLConnection.HTTP_OK
        )
//        mockNetworkResponseWithFileContent(getJsonDataFromAsset(context, "success_resp_popular_peoples.json"),
//            HttpURLConnection.HTTP_OK)
        mRepo = PopularPeoplesRepo()

        val dataReceived = mRepo.getPopularPeoples1(mParam)


        assertNotNull(dataReceived)
//        assertEquals(dataReceived.count, mCount)
//        assertEquals(dataReceived.next, mNextValue)


    }


    fun getJsonDataFromAsset(context: Context, fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return jsonString
    }


}