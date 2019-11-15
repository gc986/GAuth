package ru.gc986.gauth.p.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.gc986.gauth.m.DataCenter
import javax.inject.Inject

abstract class CommonPresImpl<T : CommonView> : CommonPres<T> {

    private var view: T? = null
    protected val unsubscribe = CompositeDisposable()
    @Inject lateinit var dataCenter: DataCenter

    abstract fun init()

    override fun setup(view: T) {
        this.view = view
        init()
    }

    override fun onDestroy() {
        unsubscribe.dispose()
    }

    override fun onPause() {
    }

    override fun onStart() {
    }

    fun Disposable.addToUnsubscribe() = unsubscribe.add(this)

    protected fun getNet() = dataCenter.getNetProvider()

    protected fun getSP() = dataCenter.getSharedPref()

}