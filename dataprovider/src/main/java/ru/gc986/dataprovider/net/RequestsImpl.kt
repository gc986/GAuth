package ru.gc986.dataprovider.net

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.gc986.dataprovider.net.common.BaseRequest
import ru.gc986.models.GitUserResult

class RequestsImpl(context: Context, showDebugInfo: Boolean) : BaseRequest(context, showDebugInfo), RequestsI {

    override fun setMainServer(mainServer: String){
        super.setMainServer(mainServer)
    }

    override fun getUsers(name: String, page: Int, perPage: Int): Observable<GitUserResult> =
        bnc.getUsers(name, page, perPage)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())


}