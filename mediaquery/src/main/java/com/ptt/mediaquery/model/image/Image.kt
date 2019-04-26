package com.ptt.mediaquery.model.image

class Image {
    var id: Int = -1
    var path: String? = null
    /**
     * the [title] with extension
     * */
    var displayName: String? = null
    /**image name
     * */
    var title: String? = null
    var size: Long = -1
    var albumID: Int = -1
}