package ru.gc986.gauth.p.common

interface CommonView {

    fun showProgress()
    fun hideProgress()
    fun showErr(th: Throwable)

}