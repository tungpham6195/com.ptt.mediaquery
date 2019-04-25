package com.ptt.mediaquery

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.ptt.mediaquery.model.image.Image

class ImageQuery(private val context: Context) {
    private var contentResolver: ContentResolver = context.contentResolver

    fun queryAllImages(): ArrayList<Image> {
        var cursor: Cursor? = null
        try {
            cursor = contentResolver.query(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                arrayOf(
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.TITLE,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.SIZE,
                    MediaStore.Images.Media.BUCKET_ID
                ),
                null, null, null
            )
            val images: ArrayList<Image> = arrayListOf()
            while (cursor.moveToNext()) {
                val image = Image()
                image.id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                image.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                image.title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE))
                image.displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
                image.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))
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
}