package com.example.yatterdroid.ui.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimelineViewModel : ViewModel() {
    private var statuses = lazy {
        MutableLiveData<MutableList<String>>().also {
            it.value = loadStatuses()
        }
    }
    /*
    // TODO: make asynchronous
    private var statuses: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>().also {
            it.value = loadStatuses() as MutableLiveData<MutableList<String>>
        }
    }
    */


    fun getStatuses(): MutableLiveData<MutableList<String>> {
        return statuses.value
    }

    private fun loadStatuses(): MutableList<String> {
        return mutableListOf<String>("These", "are", "sample")
    }

}
