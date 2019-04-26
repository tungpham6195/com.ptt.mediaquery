package com.ptt.mediaquery

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.ptt.mediaquery.model.album.Album
import com.ptt.mediaquery.model.image.Image

class ImageQuery(private val context: Context) {
    private var contentResolver: ContentResolver = context.contentResolver

    fun queryAllImages(): MutableList<Image> {
        var cursor: Cursor? = null
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val sortOrder = "ORDER BY ${MediaStore.Images.Media.BUCKET_ID} ASC"
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.BUCKET_ID
        )
        try {
            cursor = contentResolver.query(
                uri,
                projection,
                null, null, sortOrder
            )
            val images: ArrayList<Image> = arrayListOf()
            while (cursor.moveToNext()) {
                val image = Image()
                image.id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                image.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                image.title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE))
                image.displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
                image.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))
                image.albumID = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID))
                images.add(image)
            }
            return images

        } catch (e: Exception) {
            e.printStackTrace()
            return arrayListOf()
        } finally {
            cursor?.close()
        }
    }

    fun queryImageByAlbumId(albumId: Int): MutableList<Image>{
        var cursor: Cursor? = null
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val sortOrder = "ORDER BY ${MediaStore.Images.Media.BUCKET_ID} ASC"
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.BUCKET_ID
        )
        try {
            cursor = contentResolver.query(
                uri,
                projection,
                null, null, sortOrder
            )
            val images: ArrayList<Image> = arrayListOf()
            while (cursor.moveToNext()) {
                val image = Image()
                image.id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                image.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                image.title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE))
                image.displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
                image.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))
                image.albumID = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID))
                images.add(image)
            }
            return images

        } catch (e: Exception) {
            e.printStackTrace()
            return arrayListOf()
        } finally {
            cursor?.close()
        }
    }

    fun queryAllAlbums(): MutableList<Album<Image>> {

    }

    fun queryAlbums(): MutableList<Album<Image>> {
        var cursor: Cursor? = null

    }


}