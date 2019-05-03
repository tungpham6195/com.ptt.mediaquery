package com.ptt.mediaquery.repositories.image

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.ptt.mediaquery.model.album.Album
import com.ptt.mediaquery.model.image.Image
import java.io.File

class ImageRepoImpl(context: Context) : ImageRepository {
    private var contentResolver: ContentResolver = context.contentResolver

    override fun queryAllImages(): MutableList<Image> {
        var cursor: Cursor? = null
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED
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
                image.dataAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED))
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

    override fun queryAlbum(): MutableList<Album<Image>> {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val projection = arrayOf(
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.DATA
        )

        val cursor: Cursor? = contentResolver.query(
            uri,
            projection,
            null, null, sortOrder
        )
        val albums: ArrayList<Album<Image>> = arrayListOf()
        val albumSet = arrayListOf<String>()
        cursor?.apply {
            while (moveToNext()) {
                val imagePath = getString(getColumnIndex(MediaStore.Images.Media.DATA))
                val imageUri = Uri.parse(imagePath)
                val imageFile = File(imagePath)
                val pathSegment = imageUri.pathSegments
                val imageDir = pathSegment[pathSegment.indexOf(imageUri.lastPathSegment) - 1]
                if (imageFile.exists() && imageDir != null && !albumSet.contains(imageDir)) {
                    val album =
                        Album<Image>()
                    album.albumName = imageDir
                    album.albumThumbnails = imagePath
                    albums.add(album)
                    albumSet.add(album.albumName)
                }
            }
            close()
        }
        return albums
    }


}