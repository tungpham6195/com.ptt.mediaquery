package com.ptt.mediaquery.repositories.image

import com.ptt.mediaquery.model.album.Album
import com.ptt.mediaquery.model.image.Image

interface ImageRepository {
    fun queryAllImages(): MutableList<Image>

    fun queryAlbum(): MutableList<Album<Image>>
}