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
    override fun queryImageById(imageId: Int): Image? {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Images.Media._ID + " = ? "
        val selectionArgs = arrayOf("$imageId")
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED
        )
        val cursor: Cursor? = contentResolver.query(
            uri,
            projection,
            selection, selectionArgs, null
        )
        var image = Image()
        cursor?.apply {
            while (moveToNext()) {
                image = getImageDataFromCursor(this)
            }
            close()
        }
        return image
    }

    private var contentResolver: ContentResolver = context.contentResolver

    override fun queryAllImages(): MutableList<Image> {
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
        val cursor: Cursor? = contentResolver.query(
            uri,
            projection,
            null, null, sortOrder
        )
        val images = arrayListOf<Image>()
        cursor?.apply {
            while (moveToNext()) {
                val image = getImageDataFromCursor(this)
                images.add(image)
            }
            close()
        }
        return images
    }

    override fun queryAlbum(): MutableList<Album<Image>> {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.BUCKET_ID
        )

        val cursor: Cursor? = contentResolver.query(
            uri,
            projection,
            null, null, sortOrder
        )
        val albums = arrayListOf<Album<Image>>()
        cursor?.apply {
            val albumSet = arrayListOf<String>()
            while (moveToNext()) {
                //path của image
                val imagePath = getString(getColumnIndex(MediaStore.Images.Media.DATA))
                //todo dùng để lấy path segment dựa trên image path, chủ yếu để lấy được folder chứa hình
                val imageUri = Uri.parse(imagePath)

                //todo dùng để check xem file exist hay ko
                val imageFile = File(imagePath)

                //todo segment của image path, từng segment đc tách ra từ imagePath, dựa vào đó để lấy tên folder chứa image
                val pathSegments = imageUri.pathSegments

                //todo folder chứa image sẽ đứng trc tên file của image
                val imageDir = pathSegments[pathSegments.indexOf(imageUri.lastPathSegment) - 1]

                if (imageDir != null && imageFile.exists()) {//check xem file có thực sự exist hay ko
                    if (!albumSet.contains(imageDir)) {//folder chứa image ko có trong albumSet (tức chưa từng đc thêm vào albums)
                        val album = Album<Image>()
                        //todo set tên folder (tên album)
                        album.albumName = imageDir
                        //todo set path của hình đầu tiên trong album, để làm thumbnail cho album
                        album.albumThumbnail = imagePath
                        //todo tạo image object cho hình đầu tiên của album
                        val image = getImageDataFromCursor(this)
                        //todo thêm image vào list hình của album
                        album.albumItems.add(image)

                        //todo add album mới vào ds album
                        albums.add(album)

                        //todo add album vào albumSet để check những album existed khi thêm album
                        albumSet.add(album.albumName)
                    } else {
                        //todo index của album đã đc thêm trc đó
                        val albumIndex = albumSet.indexOf(imageDir)
                        //todo lấy lại album đã có trong albumSet
                        val album = albums[albumIndex]

                        //todo tạo image object cho hình đầu tiên của album
                        val image = getImageDataFromCursor(this)

                        //todo thêm image vào list hình của album
                        album.albumItems.add(image)

                        //todo update album sau khi add image
                        albums[albumIndex] = album
                    }

                }
            }
            close()
        }
        return albums
    }

    /**lấy data từ cursor*/
    private fun getImageDataFromCursor(cursor: Cursor): Image {
        val id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID))
        val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        val title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE))
        val displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
        val size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))
        val dataAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED))
        return wrapImageInfo(id, path, title, displayName, size, dataAdded)

    }

    /** set info của hình lấy đc từ device vào object [Image]*/
    private fun wrapImageInfo(
        imageId: Int, imagePath: String, imageTitle: String,
        displayName: String, imageSize: Long, dateAdded: Long
    ): Image {
        val image = Image()
        image.id = imageId
        image.path = imagePath
        image.title = imageTitle
        image.displayName = displayName
        image.size = imageSize
        image.dataAdded = dateAdded
        return image
    }
}