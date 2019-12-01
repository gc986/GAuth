package ru.gc986.gauth.p.searchUserGithub

import ru.gc986.gauth.p.common.CommonPres

interface SearchUserGithubPres: CommonPres<SearchUserGithubView> {

    fun newSearch()
    fun nextPage()

}