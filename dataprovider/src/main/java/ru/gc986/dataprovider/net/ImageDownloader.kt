package ru.gc986.dataprovider.net

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class ImageDownloader(val context: Context) {

    fun loadRoundImage(uri: Uri?, imageView: ImageView){

        Picasso.with(context)
            .load(uri?.toString())
            .placeholder(android.R.drawable.ic_menu_upload)
            .error(android.R.drawable.stat_notify_error)
            .transform(RoundedCornersTransformation(100, 0))
            .into(imageView)
    }

}