package ru.gc986.gauth.v.searchUserGithub

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search_users_github.*
import ru.gc986.gauth.GAuthApplication
import ru.gc986.gauth.R
import ru.gc986.gauth.p.searchUserGithub.SearchUserGithubPres
import ru.gc986.gauth.p.searchUserGithub.SearchUserGithubView
import ru.gc986.gauth.v.common.fragment.CommonFragment
import ru.gc986.models.GitUserResult
import java.util.concurrent.TimeUnit

class SearchUsersGithubFragment : CommonFragment<SearchUserGithubPres>(), SearchUserGithubView {

    override var userNameGitHub: String = ""
    private var adapter: UserAdapter? = null
    private lateinit var lm: LinearLayoutManager

    override fun getLayoutId(): Int = R.layout.fragment_search_users_github

    override fun init() {
        GAuthApplication.diPres.inject(this)
        getP().setup(this)
        view?.let { view ->
            ButterKnife.bind(this, view)
        }

        initViews()
    }

    private fun initViews() {
        // enter GitHub user name
        RxTextView.textChanges(etGitUser)
            .debounce(600, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userNameGitHub = it.toString()
                getP().newSearch()
            }, {
                showErr(it)
            }).addToUnsubscribe()
    }

    override fun clearResult() {
        adapter?.let {
            it.users.clear()
            it.notifyDataSetChanged()
        }
    }

    override fun addUsers(users: ArrayList<GitUserResult.GitUser>) {
        if (adapter == null) {
            context?.let { context -> adapter = UserAdapter(context, users) }
            lm = LinearLayoutManager(context)
            rvUsers.layoutManager = lm
            rvUsers.adapter = adapter
            rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    adapter?.let { adapter ->
                        if (lm.findLastVisibleItemPosition() == adapter.itemCount - 1) {
                            getP().nextPage()   // load next page
                        }
                    }
                }
            })
        } else {
            adapter?.users?.addAll(users)
            adapter?.notifyDataSetChanged()
        }
    }

}