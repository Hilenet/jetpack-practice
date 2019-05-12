package com.example.yatterdroid.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.yatterdroid.R
import com.example.yatterdroid.api.Account
import com.example.yatterdroid.api.AccountAPI
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.android.synthetic.main.status_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountname = arguments?.getString("accountname") ?: ""

        val view = inflater.inflate(R.layout.fragment_account, container, false)
        view.accountUsernameView.setText(accountname)

        loadStatuses(view, accountname)

        return view
    }

    // TODO: ViewModel使うの検討
    // 頻繁に変化する訳でもないしまあいらんのでは
    private fun loadStatuses(view: View, accountname: String) = GlobalScope.launch {
        withContext(Dispatchers.IO) {
            AccountAPI.fetchAccount(accountname).enqueue(object : Callback<Account> {
                override fun onResponse(call: Call<Account>, response: Response<Account>) {
                    val account = response.body()

                    if (account == null) {
                        // ここでbackさせたい
                        println("account not found")
                    } else {
                        // elvis演算子にはスマートキャスト利かんのクソ
                        view.accountUsernameView.setText(account.username)
                        view.accountDisplayNameView.setText(account.display_name)
                        view.accountNoteView.setText(account.note)
                    }
                }

                override fun onFailure(call: Call<Account>, t: Throwable) {}
            })
        }
    }
}

