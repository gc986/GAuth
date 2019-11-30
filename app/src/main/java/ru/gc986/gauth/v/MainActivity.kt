package ru.gc986.gauth.v

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import butterknife.ButterKnife
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import ru.gc986.dataprovider.imageDownloader.ImageDownloadHelper
import ru.gc986.gauth.GAuthApplication
import ru.gc986.gauth.R
import ru.gc986.gauth.p.main.MainPres
import ru.gc986.gauth.p.main.MainView
import ru.gc986.gauth.v.auth.GoogleAuth
import ru.gc986.gauth.v.common.Dialogs
import ru.gc986.gauth.v.common.OnAuthorized
import ru.gc986.gauth.v.common.activity.CommonActivity
import ru.gc986.models.Consts.Companion.ACTIVITY_ID_GOOGLE_SIGN_IN

class MainActivity : CommonActivity<MainPres>(), MainView {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var onAuthorized:OnAuthorized? = null

    lateinit var tvUserName: TextView
    lateinit var tvEmail: TextView
    lateinit var ivIco: ImageView

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun init() {
        ButterKnife.bind(this)
        GAuthApplication.diPres.inject(this)
        getP().setup(this)
    }

    override fun initView() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val navView: NavigationView = findViewById(R.id.navView)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_user_info
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val navViewHeader = navView.getHeaderView(0)
        tvUserName = navViewHeader.findViewById(R.id.tvUserName)
        tvEmail = navViewHeader.findViewById(R.id.tvEmail)
        ivIco = navViewHeader.findViewById(R.id.ivIco)

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null)
            signIn()
        else
            showGAuthUserInfo()
    }

    private fun signIn(){
        GoogleAuth().startAuth(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTIVITY_ID_GOOGLE_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                showGAuthUserInfo()
                onAuthorized?.invoke()
            }else {
                userIsLogout()
            }
        }
    }

    private fun showGAuthUserInfo(){
        val user = FirebaseAuth.getInstance().currentUser
        val helloUser = getString(R.string.hello_user,user?.displayName)
        tvUserName.text = helloUser
        tvEmail.text = user?.email
        ImageDownloadHelper(this).loadRoundImage(user?.photoUrl, ivIco)
    }

    fun userIsLogout(){
        tvUserName.setText(R.string.hello_user_name)
        ivIco.setImageResource(R.drawable.fui_ic_anonymous_white_24dp)
        ivIco.setBackgroundResource(R.drawable.fui_idp_button_background_anonymous)

        Dialogs(this).simpleQuest(null, R.string.google_authorization_error, {
            signIn()
        }, {
            finish()
        })
    }

}
