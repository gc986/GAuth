package ru.gc986.gauth.m

import ru.gc986.dataprovider.net.RequestsI
import ru.gc986.dataprovider.sharPref.SharedPreferencesHelper

interface DataCenter {

    fun setMainServerUrl(mainServer: String)
    fun getNetProvider(): RequestsI
    fun getSharedPref(): SharedPreferencesHelper

}