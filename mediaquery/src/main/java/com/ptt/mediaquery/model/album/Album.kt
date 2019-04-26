package com.ptt.mediaquery.model.album

class Album<Item> {
    var albumId: Int = -1
    var albumName: String? = null

    var albumItem: List<Item> = emptyList()
}