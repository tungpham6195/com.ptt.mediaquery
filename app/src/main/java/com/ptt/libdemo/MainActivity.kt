package com.ptt.libdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ptt.mediaquery.MediaQuery

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MediaQuery.instance().imageRepository.queryAlbum()
    }
}
