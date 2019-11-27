package ru.gc986.gauth.di.p

import dagger.Component
import ru.gc986.gauth.p.home.HomePresImpl
import ru.gc986.gauth.p.main.MainPresImpl
import ru.gc986.gauth.p.userInfo.UserInfoPresImpl
import javax.inject.Singleton

@Singleton
@Component(modules = [DIDataModule::class])
interface DIData {

    fun inject(presenter: MainPresImpl)
    fun inject(presenter: HomePresImpl)
    fun inject(presenter: UserInfoPresImpl)

}