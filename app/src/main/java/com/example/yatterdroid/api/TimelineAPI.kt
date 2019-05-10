package com.example.yatterdroid.api

import android.provider.Settings.Global.getString
import com.example.yatterdroid.R
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Status(val id: Int, val content: String)

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
