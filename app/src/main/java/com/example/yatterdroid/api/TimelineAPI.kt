package com.example.yatterdroid.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

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

    fun fetchTimelinePublic(): Call<List<Status>> {
        val service = restClient().create(TimelineService::class.java)
        return service.fetchPublic()
    }

}
