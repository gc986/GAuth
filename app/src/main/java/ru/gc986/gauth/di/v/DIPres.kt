package ru.gc986.gauth.di.v

import dagger.Component
import ru.gc986.gauth.v.MainActivity
import ru.gc986.gauth.v.searchUserGithub.SearchUsersGithubFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [DIPresModule::class])
interface DIPres {

    fun inject(view: MainActivity)
    fun inject(view: SearchUsersGithubFragment)

}