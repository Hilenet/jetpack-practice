package com.example.yatterdroid.ui.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yatterdroid.api.Status
import com.example.yatterdroid.api.TimelineAPI
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import kotlin.concurrent.thread


class TimelineViewModel : ViewModel() {

    var statuses: MutableLiveData<MutableList<Status>> = MutableLiveData()

    init {
        loadStatuses()
    }
//    private var statuses: MutableLiveData<List<Status>>by lazy {
//
//        MutableLiveData<List<String>>().also {
//            it.value = loadStatuses()
//        }
//    }

    // とりあえず同期的にfetch
    private fun loadStatuses() {
        lateinit var res: MutableList<Status>
        val client = TimelineAPI

        // TODO: learn coroutine and make asynchronous
        runBlocking {
            try {
                thread {
                    val li = client.fetchTimelinePublic().body()
                    if (li != null) {
                        res = li.toMutableList()
                    }
                }.join()
            } catch (e: Exception) {
                println(e)
            }
        }
        statuses = MutableLiveData<MutableList<Status>>().also { it.value = res }
    }

}