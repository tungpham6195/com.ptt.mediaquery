package com.ptt.libdemo

import com.ptt.mediaquery.ImageModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ImageModule::class])
interface MediaComponent {
    fun inject(activity: MainActivity)
}