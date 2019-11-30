package ru.gc986.dataprovider.net

import io.reactivex.Observable
import ru.gc986.models.GitUserResult

interface RequestsI {

    fun setMainServer(serverName: String)

    fun getUsers(name: String, page: Int, perPage:Int): Observable<GitUserResult>

}