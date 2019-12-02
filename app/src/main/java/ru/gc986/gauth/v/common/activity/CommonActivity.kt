package ru.gc986.gauth.v.common.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import ru.gc986.gauth.R
import ru.gc986.gauth.v.common.Dialogs
import ru.gc986.logs.Logs
import javax.inject.Inject

abstract class CommonActivity<T : Any> : AppCompatActivity(), CommonActivityView {

    @Inject
    lateinit var pres: T

    @LayoutRes
    abstract override fun getLayoutId(): Int

    private val logs: Logs = Logs()
    private val dialogs: Dialogs = Dialogs(this)

    fun getP(): T = this.pres

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        init()
        initView()
    }

    protected abstract fun initView()
    protected abstract fun init()

    override fun showProgress() {
        dialogs.showProgress()
    }

    override fun hideProgress() {
        dialogs.hideProgress()
    }

    override fun showErr(th: Throwable) {
        dialogs.showTitle(R.string.error, th.message)
    }

}