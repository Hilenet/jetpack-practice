package com.example.yatterdroid.ui.timeline

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.yatterdroid.R
import com.example.yatterdroid.api.Status
import kotlinx.android.synthetic.main.timeline_fragment.*

class TimelineFragment : Fragment() {

    companion object {
        fun newInstance() = TimelineFragment()
    }

    private lateinit var viewModel: TimelineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //　TODO: attachToRootつけたら落ちるの何故？
        return inflater.inflate(R.layout.timeline_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // live dataをobserve
        viewModel = ViewModelProviders.of(this).get(TimelineViewModel::class.java)
        viewModel.statuses.observe(this, Observer<MutableList<Status>> { list ->
            timelineSlot.adapter = StatusListAdapter(this.context!!, list)
        })
        viewModel.loadStatuses()
    }

}
