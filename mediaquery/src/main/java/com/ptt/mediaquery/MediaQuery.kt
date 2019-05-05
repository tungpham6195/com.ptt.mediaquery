package com.ptt.mediaquery

import android.app.Application
import android.content.Context
import com.ptt.mediaquery.base.DaggerMediaQueryComponent
import com.ptt.mediaquery.base.modules.image.ImageModule
import com.ptt.mediaquery.repositories.image.ImageRepository
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * đây là singleton class
 * */
class MediaQuery private constructor(context: WeakReference<Context>) {

    @Inject
    lateinit var imageRepository: ImageRepository

    init {
        DaggerMediaQueryComponent.builder().imageModule(context.get()?.let {
            ImageModule(it)
        }).build().inject(this)
    }

    private object Holder {
        val INSTANCE = MediaQuery(context)
    }

    companion object {
        private lateinit var context: WeakReference<Context>

        /**
         * todo phải init đầu tiên ở class [Application] của mỗi app
         * @param context application context của app
         * @return [MediaQuery]
         * */
        @JvmStatic
        fun init(context: Context): MediaQuery {
            this.context = WeakReference(context)
            return Holder.INSTANCE
        }

        /**
         * todo dùng để lấy get của [MediaQuery] khi muốn sử dụng các repository
         * @return [MediaQuery]
         * */
        @JvmStatic
        fun get(): MediaQuery {
            return Holder.INSTANCE
        }
    }
}
