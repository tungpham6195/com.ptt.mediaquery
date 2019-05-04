package com.ptt.mediaquery.repositories.image

import com.ptt.mediaquery.model.album.Album
import com.ptt.mediaquery.model.image.Image

interface ImageRepository {
    /**todo lấy tất cả hình ảnh*/
    fun queryAllImages(): MutableList<Image>

    /**todo lấy album và cả hình ảnh*/
    fun queryAlbum(): MutableList<Album<Image>>

    /**todo lấy hình theo id của image*/
    fun queryImageById(imageId: Int): Image?
}