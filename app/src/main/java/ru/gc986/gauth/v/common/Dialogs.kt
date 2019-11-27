package ru.gc986.gauth.v.common

import android.app.Activity
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import ru.gc986.gauth.R

class Dialogs (val context: Activity) {

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

    fun simpleQuest(@StringRes title: Int?, @StringRes message: Int?, okListener: OkListener?, cancelListener: CancelListener?){
        if (title == null && message == null) {
            throw Exception(getString(R.string.params_for_dialog_not_set))
        }
        val title = if (title!=null) getString(title) else null
        val message = if (message != null) getString(message) else null
        simpleQuest(title, message, okListener, cancelListener)
    }

    fun simpleQuest(title: String?, message: String?, okListener: OkListener?, cancelListener: CancelListener?){
        if (title == null && message == null) {
            throw Exception(getString(R.string.params_for_dialog_not_set))
        }

        val ad = AlertDialog.Builder(context)
        title?.let { ad.setTitle(it) }
        message?.let { ad.setMessage(it) }
        ad.setPositiveButton(android.R.string.ok){ _, _ ->
            okListener?.invoke()
        }
        ad.setNegativeButton(android.R.string.cancel){ _, _ ->
            cancelListener?.invoke()
        }

        ad.show()
    }

}