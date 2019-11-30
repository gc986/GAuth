package ru.gc986.gauth.v.searchUserGithub

import android.app.Dialog
import butterknife.ButterKnife
import butterknife.OnTextChanged
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search_users_github.*
import ru.gc986.gauth.R
import ru.gc986.gauth.p.home.HomePres
import ru.gc986.gauth.p.home.HomeView
import ru.gc986.gauth.v.common.Dialogs
import ru.gc986.gauth.v.common.fragment.CommonFragment
import ru.gc986.logs.Logs
import java.util.concurrent.TimeUnit

class SearchUsersGithubFragment : CommonFragment<HomePres>(), HomeView {

    override fun getLayoutId(): Int  = R.layout.fragment_search_users_github

    override fun init() {
        view?.let{view ->
            ButterKnife.bind(this, view)
        }

        initViews()
    }

    private fun initViews(){
        RxTextView.textChanges(etGitUser)
            .debounce(600, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Logs.i("OnSearch!")
            },{

            }).addToUnsubscribe()
    }

}