package com.ptt.mediaquery

import android.content.Context
import com.ptt.mediaquery.base.DaggerMediaQueryComponent
import com.ptt.mediaquery.base.modules.image.ImageModule
import com.ptt.mediaquery.repositories.image.ImageRepository
import java.lang.ref.WeakReference
import javax.inject.Inject

class MediaQuery private constructor(context: WeakReference<Context>) {

    @Inject
    lateinit var imageRepository: ImageRepository

    init {
        DaggerMediaQueryComponent.builder().imageModule(context.get()?.let {
            ImageModule(
                it
            )
        }).build().inject(this)
    }

    private object Holder {
        val INSTANCE =
            MediaQuery(context)
    }

    companion object {
        lateinit var context: WeakReference<Context>

        @JvmStatic
        fun init(context: Context): MediaQuery {
            Companion.context = WeakReference(context)
            return Holder.INSTANCE
        }

        @JvmStatic
        fun instance(): MediaQuery {
            return Holder.INSTANCE
        }
    }
}
