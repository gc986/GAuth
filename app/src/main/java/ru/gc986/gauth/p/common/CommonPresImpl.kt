package ru.gc986.gauth.p.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class CommonPresImpl<T : CommonView> : CommonPres<T> {

    private var view: T? = null
    protected val unsubscribe = CompositeDisposable()

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

}