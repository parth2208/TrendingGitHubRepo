package com.parthbhardwaj.trendinggithubrepo

import com.parthbhardwaj.trendinggithubrepo.remote.RepoApiService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.BufferedSource
import okio.Okio
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@RunWith(JUnit4::class)
open class ApiServiceTest<T> {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var apiService: RepoApiService

    @Before
    @Throws(IOException::class)
    fun mockServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory())
            .build()
            .create(RepoApiService::class.java)
    }

    @After
    @Throws(IOException::class)
    fun stopServer(){
        mockWebServer.shutdown()
    }

    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String, headers: Map<String, String>){
        val inputStream = ApiServiceTest::class.java.classLoader?.getResourceAsStream(String.format("app/%s", fileName))
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers){
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody((source as BufferedSource).readString(
            StandardCharsets.UTF_8)))
    }

    @Test
    @Throws(IOException::class)
    fun fetchPostsTest() {
        enqueueResponse("test_repositories.json")
        val response = apiService.getRepositories().blockingFirst()

        val apiResponse = response.size
        Assert.assertEquals(3, apiResponse)
    }

    private fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, emptyMap())
    }

    @Throws(InterruptedException::class)
    fun assertRequestPath(path: String) {
        val request = mockWebServer.takeRequest()
        MatcherAssert.assertThat(request.path, CoreMatchers.`is`(path))
    }
}
