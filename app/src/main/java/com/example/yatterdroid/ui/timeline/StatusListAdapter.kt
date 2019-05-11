package com.example.yatterdroid.ui.timeline

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.yatterdroid.R
import com.example.yatterdroid.api.Status

class StatusListAdapter (context: Context, statuses: List<Status>) : ArrayAdapter<Status>(context, 0, statuses) {
    // layout取得用
    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // positionがindex，list itemを取得しにくる
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // アクセスするindexを末尾オリジンに
        val index = count-position-1

        var view = convertView
        var holder: ViewHolder

        if (view == null) {
            view = layoutInflater.inflate(R.layout.status_item, parent, false)
            holder = ViewHolder(
                view.findViewById(R.id.accountnameView)!!,
                view.findViewById(R.id.statusContentView),
                view.findViewById(R.id.statusIconView)
            )
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val status = getItem(index) as Status
        holder.accountnameView.text = status.account.display_name
        holder.statusContentView.text = status.content
        // holder.statusIconView.setImageBitmap(    status.account.avatar as imageId  )

        return view!!
    }
}
data class ViewHolder(val accountnameView: TextView, val statusContentView: TextView, val statusIconView: ImageView)
