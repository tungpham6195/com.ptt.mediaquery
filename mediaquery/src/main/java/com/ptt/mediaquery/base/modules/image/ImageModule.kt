package com.ptt.mediaquery.base.modules.image

import android.content.Context
import com.ptt.mediaquery.repositories.image.ImageRepoImpl
import com.ptt.mediaquery.repositories.image.ImageRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideImageRepository(): ImageRepository {
        return ImageRepoImpl(context)
    }
}