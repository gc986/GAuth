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

    abstract override @LayoutRes fun getLayoutId(): Int

    private val logs: Logs = Logs()
    private val dialogs: Dialogs = Dialogs(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initView()
    }

    abstract protected fun initView()

}