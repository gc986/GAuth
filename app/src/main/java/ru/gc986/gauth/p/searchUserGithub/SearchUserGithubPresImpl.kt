package ru.gc986.gauth.p.searchUserGithub

import ru.gc986.gauth.GAuthApplication
import ru.gc986.gauth.p.common.CommonPresImpl
import ru.gc986.models.Consts
import ru.gc986.models.Consts.Companion.PERPAGE

class SearchUserGithubPresImpl : CommonPresImpl<SearchUserGithubView>(), SearchUserGithubPres {

    private var nextPage = Consts.FIRSTPAGE

    override fun init() {
        GAuthApplication.diData.inject(this)
    }

    override fun newSearch() {
            if (getV().userNameGitHub.isNotEmpty()) {
            getV().clearResult()
            nextPage = Consts.FIRSTPAGE
            nextPage()
        } else getV().clearResult()
    }

    override fun nextPage() {
        getNet().getUsers(getV().userNameGitHub, nextPage, PERPAGE)
            .doOnSubscribe { getV().showProgress() }
            .doFinally { getV().hideProgress() }
            .subscribe({result ->
                result.items?.let { users ->
                    if (users.isNotEmpty())
                        getV().addUsers(users)
                }
                if (result.totalUsers/PERPAGE > nextPage)
                    nextPage++
            }, {
                getV().showErr(it)
            }).addToUnsubscribe()

    }

}