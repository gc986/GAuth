package ru.gc986.gauth.v.auth

import android.app.Activity
import com.firebase.ui.auth.AuthUI
import ru.gc986.models.Consts.Companion.ACTIVITY_ID_GOOGLE_SIGN_IN

class GoogleAuth {

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
            ACTIVITY_ID_GOOGLE_SIGN_IN)
    }

}