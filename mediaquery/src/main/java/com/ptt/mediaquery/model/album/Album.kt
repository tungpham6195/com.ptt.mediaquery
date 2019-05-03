package com.ptt.mediaquery.model.album

class Album<Item> {
    lateinit var albumName: String
    var albumThumbnails: String? = null
    var albumItem: List<Item> = emptyList()
}