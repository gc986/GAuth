package ru.gc986.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class GitUserResult {

    @SerializedName("total_count")
    val totalCount: Int = 0
    @SerializedName("incomplete_results")
    val incompliteResult: Boolean = false


    inner class GitUser{
        val login: String? = null
        @SerializedName("avatar_url")
        val avatar: String? = null
        val score: BigDecimal? = null
    }

}