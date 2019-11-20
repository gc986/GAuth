package ru.gc986.gauth.v.userInfo

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_user_info.*
import ru.gc986.dataprovider.imageDownloader.DownloadCallback
import ru.gc986.dataprovider.imageDownloader.ImageDownloadHelper
import ru.gc986.gauth.R
import ru.gc986.gauth.v.common.Dialogs


class UserInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_user_info, container, false)
        ButterKnife.bind(this, root)
        return root
    }

    override fun onResume() {
        super.onResume()
        showGAuthUserInfo()
    }

    private fun showGAuthUserInfo(){
        context?.let {context ->
            val user = FirebaseAuth.getInstance().currentUser
            val helloUser = getString(R.string.hello_user,user?.displayName)
            tvUserName.text = helloUser
            ImageDownloadHelper(context)
                .loadRoundImage(user?.photoUrl, ivIco)
        }

    }

    @OnClick(R.id.btLogout)
    fun logout(){
        context?.let {context ->
            AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener {
                    Dialogs(activity as Activity).showTitle(R.string.logout_completed)
                }
        }
    }

}
