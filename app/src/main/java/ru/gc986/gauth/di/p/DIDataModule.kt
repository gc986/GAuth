package ru.gc986.gauth.di.p

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.gc986.gauth.BuildConfig
import ru.gc986.gauth.m.DataCenter
import ru.gc986.gauth.m.DataCenterImpl
import javax.inject.Singleton

@Module
class DIDataModule(internal var context: Context) {

    @Provides
    @Singleton
    fun provideRequests(): DataCenter {
        val dc = DataCenterImpl(context)
        dc.setMainServerUrl(BuildConfig.MainServer)
        return dc
    }

}