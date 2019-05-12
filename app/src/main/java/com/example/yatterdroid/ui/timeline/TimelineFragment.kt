package com.example.yatterdroid.ui.timeline

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.yatterdroid.R
import com.example.yatterdroid.api.Status
import kotlinx.android.synthetic.main.fragment_timeline.*
import kotlinx.android.synthetic.main.status_item.view.*


class TimelineFragment : Fragment() {
    private lateinit var viewModel: TimelineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //　TODO: attachToRootつけたら落ちるの何故？
        var view = inflater.inflate(R.layout.fragment_timeline, container, false)
        view.findViewById<ListView>(R.id.timelineSlot).setOnItemClickListener { parent, view, position, id ->
            view.statusIconView.setOnClickListener {
                findNavController(this).navigate(R.id.action_timeline_account)
            }
        }

        return view
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
