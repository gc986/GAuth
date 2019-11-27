package ru.gc986.gauth.p.home

import ru.gc986.gauth.GAuthApplication
import ru.gc986.gauth.p.common.CommonPresImpl

class HomePresImpl : CommonPresImpl<HomeView>(), HomePres {

    override fun init() {
        GAuthApplication.diData.inject(this)
    }

}