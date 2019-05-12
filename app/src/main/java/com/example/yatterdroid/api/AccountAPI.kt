package com.example.yatterdroid.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface AccountService {
    @GET("v1/accounts/{accountname}")
    fun fetch(@Path("accountname") account: String): Call<Account>

}

object AccountAPI {
    private fun restClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hile.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun fetchAccount(name: String): Call<Account> {
        val service = restClient().create(AccountService::class.java)
        return service.fetch(name)
    }

}
