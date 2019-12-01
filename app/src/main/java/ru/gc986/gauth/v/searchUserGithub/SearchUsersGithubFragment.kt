package ru.gc986.gauth.v.searchUserGithub

import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search_users_github.*
import ru.gc986.gauth.R
import ru.gc986.gauth.p.searchUserGithub.SearchUserGithubPres
import ru.gc986.gauth.p.searchUserGithub.SearchUserGithubView
import ru.gc986.gauth.v.common.fragment.CommonFragment
import ru.gc986.models.GitUserResult
import java.util.concurrent.TimeUnit

class SearchUsersGithubFragment : CommonFragment<SearchUserGithubPres>(), SearchUserGithubView {

    override var userNameGitHub: String = ""
    private var adapter: UserAdapter? = null

    override fun getLayoutId(): Int  = R.layout.fragment_search_users_github

    override fun init() {
        view?.let{view ->
            ButterKnife.bind(this, view)
        }

        initViews()
    }

    private fun initViews(){
        // enter GitHub user name
        RxTextView.textChanges(etGitUser)
            .debounce(600, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userNameGitHub = it.toString()
                getP().newSearch()
            },{

            }).addToUnsubscribe()
    }

    override fun clearResult() {

    }

    override fun addUsers(users: ArrayList<GitUserResult.GitUser>) {
        if (adapter==null){
            context?.let { context ->  adapter = UserAdapter(context, users) }
            rvUsers.layoutManager = LinearLayoutManager(context)
            rvUsers.adapter = adapter
        }
    }

}