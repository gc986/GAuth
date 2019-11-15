package ru.gc986.gauth.di.p

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DIDataModule::class])
interface DIData {
}