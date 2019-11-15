package ru.gc986.gauth.p.main

import ru.gc986.gauth.GAuthApplication
import ru.gc986.gauth.p.common.CommonPresImpl

class MainPresImpl: CommonPresImpl<MainView>(), MainPres {

    override fun init() {
        GAuthApplication.diData.inject(this)
    }

}