package com.example.yatterdroid.ui.yatter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yatterdroid.R

class YatterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //　TODO: attachToRootつけたら落ちるの何故？
        return inflater.inflate(R.layout.fragment_yatter, container, false)
    }
}
