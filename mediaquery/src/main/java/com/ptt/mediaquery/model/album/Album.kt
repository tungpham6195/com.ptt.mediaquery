package com.ptt.mediaquery.model.album

class Album<Item> {
    lateinit var albumName: String
    var albumThumbnail: String? = null
    var albumItems: MutableList<Item> = arrayListOf()
}