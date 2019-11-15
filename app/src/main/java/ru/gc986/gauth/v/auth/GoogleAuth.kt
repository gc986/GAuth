package ru.gc986.gauth.v.auth

import android.app.Activity
import com.firebase.ui.auth.AuthUI

class GoogleAuth {

    companion object{
        val RC_SIGN_IN = 1000
    }

    fun startAuth(activity: Activity){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        activity.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

}