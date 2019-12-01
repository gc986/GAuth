package ru.gc986.dataprovider.imageDownloader

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class ImageDownloadHelper(val context: Context) {

    fun loadRoundImage(uri: Uri?, imageView: ImageView, callback: DownloadCallback? = null){
        loadRoundImage(uri.toString(), imageView, callback)
    }

    fun loadRoundImage(uri: String?, imageView: ImageView, callback: DownloadCallback? = null){

        Picasso.with(context)
            .load(uri?.toString())
            .placeholder(android.R.drawable.ic_menu_upload)
            .error(android.R.drawable.stat_notify_error)
            .transform(RoundedCornersTransformation(100, 0))
            .into(imageView, object : com.squareup.picasso.Callback {
                override fun onError() {
                    callback?.invoke(false)
                }
                override fun onSuccess() {
                    imageView.setBackground(null)
                    callback?.invoke(true)
                }
            })
    }

}