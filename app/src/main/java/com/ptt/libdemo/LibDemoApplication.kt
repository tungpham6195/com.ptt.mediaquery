package com.ptt.libdemo

import android.app.Application
import com.ptt.mediaquery.MediaQuery

class LibDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MediaQuery.init(applicationContext)
    }
}