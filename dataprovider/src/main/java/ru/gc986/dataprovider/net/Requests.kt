package ru.gc986.dataprovider.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gc986.models.GitUserResult

interface Requests {

    @GET("search/users")
    fun getUsers(@Query("q") name: String, @Query ("page") page: Int, @Query ("per_page") perPage:Int): Observable<GitUserResult>

}