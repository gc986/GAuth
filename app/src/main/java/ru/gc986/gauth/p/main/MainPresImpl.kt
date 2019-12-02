package ru.gc986.gauth.p.main

import com.google.firebase.auth.FirebaseAuth
import ru.gc986.gauth.GAuthApplication
import ru.gc986.gauth.p.common.CommonPresImpl

class MainPresImpl: CommonPresImpl<MainView>(), MainPres {

    override fun init() {
        GAuthApplication.diData.inject(this)
    }

    override fun checkAuth(){
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null)
            getV().signIn()
        else
            getV().showGAuthUserInfo()
    }

}