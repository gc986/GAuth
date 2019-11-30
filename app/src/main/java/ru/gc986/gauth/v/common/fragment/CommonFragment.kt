package ru.gc986.gauth.v.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.gc986.logs.Logs
import javax.inject.Inject

abstract class CommonFragment<T : Any> : Fragment(), CommonFragmentView {

    @Inject
    lateinit var pres: T
    var logs: Logs = Logs()
    protected val unsubscribe = CompositeDisposable()

    abstract override fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe.dispose()
    }

    fun Disposable.addToUnsubscribe() = unsubscribe.add(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    abstract fun init()

}