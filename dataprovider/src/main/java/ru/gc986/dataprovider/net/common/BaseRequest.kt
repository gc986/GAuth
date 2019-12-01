package ru.gc986.dataprovider.net.common

import android.content.Context
import ru.gc986.dataprovider.net.Requests
import ru.gc986.dataprovider.sharPref.SharedPreferencesHelperImpl
import ru.gc986.logs.Logs
import ru.gc986.models.Consts.Companion.SERVERADDRESS

open class BaseRequest(context: Context, showDebugInfo: Boolean) {

    private val baseNetConstructor: BaseNetConstructor = BaseNetConstructor(showDebugInfo)
    private var mainServer: String? = null
    protected val logs = Logs()
    protected var sharedPreferencesHelperImpl = SharedPreferencesHelperImpl(context)

    protected val bnc: Requests
        get() = baseNetConstructor.create(getMainServer(), Requests::class.java)

    open fun setMainServer(url: String) {
        mainServer = url
    }

    fun getMainServer(): String {
        mainServer ?: let {
            sharedPreferencesHelperImpl.getSPString(SERVERADDRESS)
                .subscribe {
                    mainServer = it
                }
        }
        return mainServer!!
    }

}
