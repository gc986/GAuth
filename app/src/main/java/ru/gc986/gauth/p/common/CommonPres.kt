package ru.gc986.gauth.p.common

interface CommonPres<T : CommonView>{

    fun setup(view: T)
    fun onDestroy()
    fun onPause()
    fun onStart()

}