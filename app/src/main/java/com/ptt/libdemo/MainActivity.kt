package com.ptt.libdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ptt.mediaquery.ImageModule
import com.ptt.mediaquery.repositories.image.ImageRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var imageRepository: ImageRepository

    lateinit var mediaComponent: MediaComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaComponent = DaggerMediaComponent.builder().imageModule(
            ImageModule(
                applicationContext
            )
        ).build()
        mediaComponent.inject(this)
        imageRepository.queryAlbum()
    }
}
