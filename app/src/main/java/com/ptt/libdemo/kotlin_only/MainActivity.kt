package com.ptt.libdemo.kotlin_only

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ptt.libdemo.R
import com.ptt.mediaquery.MediaQuery

@SuppressLint("Registered")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MediaQuery.get().imageRepository.queryAlbum()
    }
}
