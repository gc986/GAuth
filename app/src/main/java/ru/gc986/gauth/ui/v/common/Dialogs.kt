package ru.gc986.gauth.ui.v.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

class Dialogs (val context: Context) {

    private fun getString(@StringRes resId: Int) = context.getString(resId)

    fun showTitle(@StringRes resId: Int, okListener:OkListener? = null){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(resId))
            .setCancelable(false)
            .setNegativeButton(android.R.string.ok
            ) { _, _ -> okListener?.invoke() }
        val alert = builder.create()
        alert.show()
    }

}