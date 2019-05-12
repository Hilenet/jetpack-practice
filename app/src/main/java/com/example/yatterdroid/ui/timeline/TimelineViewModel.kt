package com.example.yatterdroid.ui.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yatterdroid.api.Status
import com.example.yatterdroid.api.TimelineAPI
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TimelineViewModel : ViewModel() {
    // delegateはvalのみっぽい
    var statuses: MutableLiveData<MutableList<Status>> = MutableLiveData<MutableList<Status>>().also {
        it.value = mutableListOf()
    }

    init {
        loadStatuses()
    }

    // いい感じにhandleしてpostValueまで
    private fun addStatuses(diff: List<Status>?) {
        diff ?: return
        val before = if (statuses.value != null) statuses.value!! else mutableListOf()
        val result = (before + diff).toMutableList()

        if (before != result) {
            statuses.postValue(result)
        }
    }

    // 同期的にfetchして追加
    public fun fetchStatuses() = runBlocking {
        val res = GlobalScope.async(Dispatchers.IO) {
            TimelineAPI.fetchTimelinePublic().execute().body()
        }.await()

        res ?: return@runBlocking
        addStatuses(res)
    }

    // 非同期にfetchして追加
    public fun loadStatuses() = GlobalScope.launch {
        withContext(Dispatchers.IO) {
            TimelineAPI.fetchTimelinePublic().enqueue(object : Callback<List<Status>> {
                override fun onResponse(call: Call<List<Status>>, response: Response<List<Status>>) {
                    addStatuses(response.body())
                }

                override fun onFailure(call: Call<List<Status>>, t: Throwable) {}
            })
        }
    }

}