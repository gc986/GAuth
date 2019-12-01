package ru.gc986.gauth.p.searchUserGithub

import ru.gc986.gauth.p.common.CommonView
import ru.gc986.models.GitUserResult

interface SearchUserGithubView: CommonView {

    var userNameGitHub: String
    fun clearResult()
    fun addUsers(items: ArrayList<GitUserResult.GitUser>)

}