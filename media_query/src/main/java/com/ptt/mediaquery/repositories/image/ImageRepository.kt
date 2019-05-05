package com.ptt.mediaquery.repositories.image

import com.ptt.mediaquery.model.album.Album
import com.ptt.mediaquery.model.image.Image

interface ImageRepository {
    /**todo lấy tất cả hình ảnh
     * @return [MutableList] của [Image]
     * */
    fun queryAllImages(): MutableList<Image>

    /**todo lấy album và cả hình ảnh
     * @return [MutableList] của [Album]
     * */
    fun queryAlbum(): MutableList<Album<Image>>

    /**todo lấy hình theo id của image
     * @return [Image]
     * @param imageId id của hình muốn tìm*/
    fun queryImageById(imageId: Int): Image?
}