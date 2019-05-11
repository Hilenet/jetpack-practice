package com.example.yatterdroid.ui.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yatterdroid.api.Status
import com.example.yatterdroid.api.TimelineAPI
import kotlinx.coroutines.*
import java.lang.Exception


class TimelineViewModel : ViewModel() {
    // delegate使ってよい感じに
    var statuses: MutableLiveData<MutableList<Status>> = MutableLiveData()

    init {
        loadStatuses()
    }

    // とりあえず同期的にfetch
    private fun loadStatuses() {
        lateinit var res: MutableList<Status>
        val client = TimelineAPI

        // TODO: learn coroutine and make asynchronous
        runBlocking {
            try {
                // IOを別スレッドにdispatchしてくれるように
                launch(context = Dispatchers.IO, block = {

                    val li = client.fetchTimelinePublic().body()
                    if (li != null) {
                        res = li.toMutableList()
                    }
                })
            } catch (e: Exception) {
                println(e)
            }
        }
        statuses.value = res
    }

}