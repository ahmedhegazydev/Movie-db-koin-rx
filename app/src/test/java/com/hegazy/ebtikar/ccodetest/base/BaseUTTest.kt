package com.dev.ccodetest.base

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import timber.log.Timber
import java.io.File

/**
 * Creates base
 */
abstract class BaseUTTest : KoinTest {

    /**
     * For MockWebServer instance
     */
    private lateinit var mMockServerInstance: MockWebServer

    /**
     * Default, let server be shut down
     */
    private var mShouldStart = false

    @Before
    open fun setUp() {
        startMockServer(true)
    }

    /**
     * Helps to read input file returns the respective data in mocked call
     */
    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) =
        mMockServerInstance.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
//                .setBody(content)
                .setBody(getJson(fileName))
//                .setBody(getJsonDataFromAsset(fileName))
        )

    /**
     * Reads input file and converts to json
     */
    fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        Timber.d("file = $file")
        return String(file.readBytes())
    }


    /**
     * Start Mockwebserver
     */
    private fun startMockServer(shouldStart: Boolean) {
        if (shouldStart) {
            mShouldStart = shouldStart
            mMockServerInstance = MockWebServer()
            mMockServerInstance.start()
        }
    }

    /**
     * Set Mockwebserver url
     */
    fun getMockWebServerUrl() = mMockServerInstance.url("/").toString()

    /**
     * Stop Mockwebserver
     */
    private fun stopMockServer() {
        if (mShouldStart) {
            mMockServerInstance.shutdown()
        }
    }

    @After
    open fun tearDown() {
        //Stop Mock server
        stopMockServer()
        //Stop Koin as well
        stopKoin()
    }
}