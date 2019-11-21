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
import ru.gc986.gauth.v.MainActivity
import ru.gc986.gauth.v.auth.GoogleAuth
import ru.gc986.gauth.v.common.Dialogs
import ru.gc986.gauth.v.common.OnAuthorized


class UserInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_user_info, container, false)
        ButterKnife.bind(this, root)
        return root
    }

    override fun onResume() {
        super.onResume()
        showGAuthUserInfo()
        (activity as MainActivity).onAuthorized = onAuthorized
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).onAuthorized = null
    }

    private val onAuthorized:OnAuthorized = {
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
        context?.let { context ->
            GoogleAuth().logout(context){
                Dialogs(activity as Activity).showTitle(R.string.logout_completed){
                    clearUserInfo()
                    (activity as MainActivity).userIsLogout()
                }
            }
        }
    }

    private fun clearUserInfo(){
        tvUserName.setText(R.string.hello_user_name)
        ivIco.setImageResource(R.drawable.fui_ic_anonymous_white_24dp)
        ivIco.setBackgroundResource(R.drawable.fui_idp_button_background_anonymous)
    }

}
