package ru.gc986.gauth.v.common

import android.app.Activity
import android.app.ProgressDialog
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import ru.gc986.gauth.R

class Dialogs(val context: Activity) {

    private var progressDialog: AlertDialog? = null

    private fun getString(@StringRes resId: Int) = context.getString(resId)

    fun showTitle(title: String?, message: String?, okListener: OkListener? = null) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setNegativeButton(
                android.R.string.ok
            ) { _, _ -> okListener?.invoke() }
        val alert = builder.create()
        alert.show()
    }

    fun showTitle(
        @StringRes titleId: Int, message: String? = null,
        okListener: OkListener? = null
    ) {
        showTitle(getString(titleId), message, okListener)
    }

    fun simpleQuest(
        @StringRes title: Int?, @StringRes message: Int?, okListener: OkListener?,
        cancelListener: CancelListener?
    ) {
        if (title == null && message == null) {
            throw Exception(getString(R.string.params_for_dialog_not_set))
        }
        val title = if (title != null) getString(title) else null
        val message = if (message != null) getString(message) else null
        simpleQuest(title, message, okListener, cancelListener)
    }

    fun simpleQuest(
        title: String?,
        message: String?,
        okListener: OkListener?,
        cancelListener: CancelListener?
    ) {
        if (title == null && message == null) {
            throw Exception(getString(R.string.params_for_dialog_not_set))
        }

        val ad = AlertDialog.Builder(context)
        title?.let { ad.setTitle(it) }
        message?.let { ad.setMessage(it) }
        ad.setPositiveButton(android.R.string.ok) { _, _ ->
            okListener?.invoke()
        }
        ad.setNegativeButton(android.R.string.cancel) { _, _ ->
            cancelListener?.invoke()
        }

        ad.show()
    }

    fun showProgress() {
        if (progressDialog == null) {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false);
            builder.setView(dialogView);

            progressDialog = builder.create()
            progressDialog?.show()
        }
    }

    fun hideProgress() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

}