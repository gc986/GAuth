package ru.gc986.gauth.v

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.nav_header_main.*
import ru.gc986.dataprovider.net.ImageDownloader
import ru.gc986.gauth.R
import ru.gc986.gauth.p.main.MainPres
import ru.gc986.gauth.p.main.MainView
import ru.gc986.gauth.v.auth.GoogleAuth
import ru.gc986.gauth.v.common.Dialogs
import ru.gc986.gauth.v.common.activity.CommonActivity

class MainActivity : CommonActivity<MainPres>(), MainView {

    override fun getLayoutId(): Int = R.layout.activity_main

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun initView() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        GoogleAuth().startAuth(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GoogleAuth.RC_SIGN_IN) {

            if (resultCode == Activity.RESULT_OK)
                showGAuthUserInfo(FirebaseAuth.getInstance().currentUser)
            else
                Dialogs(this).showTitle(R.string.google_authorization_error){
                    finish()
                }
        }
    }

    private fun showGAuthUserInfo(user: FirebaseUser?){
        val helloUser = getString(R.string.hello_user,user?.displayName)
        tvUserName.text = helloUser

        ImageDownloader(this).loadRoundImage(FirebaseAuth.getInstance().currentUser?.photoUrl, ivIco)
    }



}
