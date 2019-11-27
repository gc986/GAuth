package ru.gc986.gauth.v.home

import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_home.*
import ru.gc986.gauth.R
import ru.gc986.gauth.p.home.HomePres
import ru.gc986.gauth.p.home.HomeView
import ru.gc986.gauth.v.common.fragment.CommonFragment

class HomeFragment : CommonFragment<HomePres>(), HomeView {

    override fun init() {
        view?.let{view ->
            ButterKnife.bind(this, view)
        }

        text_home.setText(R.string.app_name)
    }

    override fun getLayoutId(): Int  = R.layout.fragment_home

}