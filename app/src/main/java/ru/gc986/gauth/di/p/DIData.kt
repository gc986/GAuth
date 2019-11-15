package ru.gc986.gauth.di.p

import dagger.Component
import ru.gc986.gauth.p.main.MainPresImpl
import javax.inject.Singleton

@Singleton
@Component(modules = [DIDataModule::class])
interface DIData {

    fun inject(presenter: MainPresImpl)

}