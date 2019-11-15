package ru.gc986.gauth.di.v

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DIPresModule::class])
interface DIPres {
}