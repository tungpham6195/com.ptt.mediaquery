package com.ptt.libdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ptt.mediaquery.ImageQuery

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        ImageQuery(applicationContext).queryAllImages()
    }
}
