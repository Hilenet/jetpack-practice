package com.example.yatterdroid.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

data class StatusReq(var status: String)

interface StatusService {
    @Headers("Authentication:username hile")
    @POST("v1/statuses")
    fun postStatus(@Header("Authentication") authentication: String, @Body status: StatusReq): Call<Status>
}

object StatusAPI {
    private fun restClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hile.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun postStatus(value: String): Call<Status> {
        val service = restClient().create(StatusService::class.java)
        return service.postStatus("hile", StatusReq(value))
    }
}
