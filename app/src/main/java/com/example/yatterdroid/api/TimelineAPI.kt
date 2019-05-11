package com.example.yatterdroid.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Account(var username: String, var display_name: String, var avatar: String)
data class Status(var id: Int, var content: String, var account: Account)

interface TimelineService {
    @GET("v1/timelines/public")
    fun fetchPublic(): Call<List<Status>>
}

object TimelineAPI {

    private fun restClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hile.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun fetchTimelinePublic(): Response<List<Status>> {
        val service = restClient().create(TimelineService::class.java)
        val tl = service.fetchPublic().execute()
        return tl
    }

}
