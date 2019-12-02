package ru.gc986.gauth.m

import android.content.Context
import androidx.annotation.StringRes
import ru.gc986.dataprovider.net.RequestsI
import ru.gc986.dataprovider.net.RequestsImpl
import ru.gc986.dataprovider.sharPref.SharedPreferencesHelper
import ru.gc986.dataprovider.sharPref.SharedPreferencesHelperImpl
import ru.gc986.gauth.GAuthApplication
import ru.gc986.gauth.R
import ru.gc986.models.Consts

class DataCenterImpl(private val context: Context): DataCenter {

    private var requests: RequestsImpl? = null
    private var mainServerUrl: String? = null
    private val sharedPref: SharedPreferencesHelper = SharedPreferencesHelperImpl(context)

    override fun setMainServerUrl(mainServer: String) {
        if (mainServer.isEmpty())
            throw NullPointerException(getString(R.string.url_server_mist_not_be_null))
        val fullMainServerUrl = if (mainServer.last().toString() != "/")
            "$mainServer/"
        else
            mainServer

        getSharedPref().putSPString(Consts.SERVERADDRESS, fullMainServerUrl).subscribe {}
        mainServerUrl = fullMainServerUrl
        requests?.setMainServer(fullMainServerUrl)
    }

    private fun getMainServerUrl(): String = mainServerUrl?.let { it } ?: let {
        var url = ""
        getSharedPref().getSPString(Consts.SERVERADDRESS).subscribe { url = it }
        mainServerUrl = url
        return url
    }

    private fun getRequests(): RequestsImpl = requests ?: let {
        val newRequests = RequestsImpl(context, GAuthApplication.showDebugInfo)
        newRequests.setMainServer(getMainServerUrl())
        requests = newRequests
        return newRequests
    }

    override fun getNetProvider(): RequestsI = getRequests()

    override fun getSharedPref(): SharedPreferencesHelper = sharedPref

    private fun getString(@StringRes stringId: Int) = context.getString(stringId)

}