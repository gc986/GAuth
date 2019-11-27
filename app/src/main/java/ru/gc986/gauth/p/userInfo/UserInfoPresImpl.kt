package ru.gc986.gauth.p.userInfo

import ru.gc986.gauth.GAuthApplication
import ru.gc986.gauth.p.common.CommonPresImpl

class UserInfoPresImpl : CommonPresImpl<UserInfoView>(), UserInfoPres {
    override fun init() {
        GAuthApplication.diData.inject(this)
    }
}