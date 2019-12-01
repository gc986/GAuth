package ru.gc986.gauth.v.userInfo

import android.app.Activity
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_user_info.*
import ru.gc986.dataprovider.imageDownloader.ImageDownloadHelper
import ru.gc986.gauth.R
import ru.gc986.gauth.p.userInfo.UserInfoPres
import ru.gc986.gauth.p.userInfo.UserInfoView
import ru.gc986.gauth.v.MainActivity
import ru.gc986.gauth.v.auth.GoogleAuth
import ru.gc986.gauth.v.common.Dialogs
import ru.gc986.gauth.v.common.OnAuthorized
import ru.gc986.gauth.v.common.fragment.CommonFragment


class UserInfoFragment : CommonFragment<UserInfoPres>(), UserInfoView {

    override fun init() {
        view?.let {view ->
            ButterKnife.bind(this, view)
        }
    }

    override fun getLayoutId(): Int  = R.layout.fragment_user_info

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
            tvEmail.text = user?.email
            ImageDownloadHelper(context)
                .loadRoundImage(user?.photoUrl, ivIco)
        }

    }

    @OnClick(R.id.btLogout)
    fun logout(){
        context?.let { context ->
            GoogleAuth().logout(context){
                dialogs.showTitle(R.string.logout_completed){
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
