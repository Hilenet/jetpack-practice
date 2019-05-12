package com.example.yatterdroid.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.yatterdroid.R

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //　TODO: attachToRootつけたら落ちるの何故？
        return inflater.inflate(R.layout.fragment_account, container, false)
    }
}
