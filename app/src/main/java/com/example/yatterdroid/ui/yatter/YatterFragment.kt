package com.example.yatterdroid.ui.yatter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.yatterdroid.R
import com.example.yatterdroid.api.Status
import com.example.yatterdroid.api.StatusAPI
import kotlinx.android.synthetic.main.fragment_yatter.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class YatterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_yatter, container, false)
        view.yatterButton.setOnClickListener {
            val value = view.yatterValue.text.toString()

            val status: Status? = postStatus(value)
            if (status == null) {
                Toast.makeText(this.context, "something went wrong", Toast.LENGTH_LONG).show();
            } else {
                NavHostFragment.findNavController(this).popBackStack()
            }
        }


        return view
    }

    // return nullable status
    private fun postStatus(value: String) = runBlocking {
        val res = withContext(Dispatchers.IO) {
            StatusAPI.postStatus(value).execute()
        }
        if (!res.isSuccessful) {
            return@runBlocking null
        }
        return@runBlocking res.body()
    }
}
