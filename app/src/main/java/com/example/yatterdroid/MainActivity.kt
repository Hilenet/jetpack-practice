package com.example.yatterdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yatterdroid.ui.timeline.TimelineFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // appContainerに代入
        // mainActivityの一要素として，飽くまでContainerの中
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.appContainer, TimelineFragment.newInstance())
                .commitNow()
        }
    }
}
