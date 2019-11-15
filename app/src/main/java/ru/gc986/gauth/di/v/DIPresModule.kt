package ru.gc986.gauth.di.v

import dagger.Module
import dagger.Provides
import ru.gc986.gauth.p.main.MainPres
import ru.gc986.gauth.p.main.MainPresImpl

@Module
class DIPresModule {

    @Provides fun provideMainPres(): MainPres = MainPresImpl()

}