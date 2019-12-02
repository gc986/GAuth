package ru.gc986.gauth

import android.app.Application
import ru.gc986.gauth.di.p.DIData
import ru.gc986.gauth.di.p.DIDataModule
import ru.gc986.gauth.di.p.DaggerDIData
import ru.gc986.gauth.di.v.DIPres
import ru.gc986.gauth.di.v.DIPresModule
import ru.gc986.gauth.di.v.DaggerDIPres
import ru.gc986.logs.Logs

class GAuthApplication: Application() {

    companion object{
        lateinit var instance: GAuthApplication
        internal lateinit var diData: DIData
        internal lateinit var diPres: DIPres

        var showDebugInfo: Boolean = BuildConfig.ShowDebugInfo
    }

    init {
        instance = this

        diData = buildData()
        diPres = buildPres()
        Logs.enable = showDebugInfo
    }

    private fun buildData(): DIData {
        return DaggerDIData.builder()
            .dIDataModule(DIDataModule(this)).build()
    }

    private fun buildPres(): DIPres {
        return DaggerDIPres.builder()
            .dIPresModule(DIPresModule()).build()
    }


}