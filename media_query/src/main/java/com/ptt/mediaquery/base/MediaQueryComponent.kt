package com.ptt.mediaquery.base

import com.ptt.mediaquery.MediaQuery
import com.ptt.mediaquery.base.modules.image.ImageModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ImageModule::class])
interface MediaQueryComponent {
    fun inject(mediaQuery: MediaQuery)
}