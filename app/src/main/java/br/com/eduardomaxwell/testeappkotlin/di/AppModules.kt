package br.com.eduardomaxwell.testeappkotlin.di

import br.com.eduardomaxwell.testeappkotlin.data.UserRepository
import br.com.eduardomaxwell.testeappkotlin.data.local.UserDatabase
import br.com.eduardomaxwell.testeappkotlin.ui.user.adduser.UserViewModel
import br.com.eduardomaxwell.testeappkotlin.ui.user.userlist.UsersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    fun load() {
        loadKoinModules(addViewModelModule() + usersViewModel() + repositoryModule() + daoModule())
    }

    private fun addViewModelModule(): Module {
        return module {
            viewModel { UserViewModel(get()) }
        }
    }

    private fun usersViewModel(): Module {
        return module {
            viewModel { UsersViewModel(get()) }
        }
    }

    private fun repositoryModule(): Module {
        return module {
            single { UserRepository(get()) }
        }
    }

    private fun daoModule(): Module {
        return module {
            single { UserDatabase.getDatabase(androidContext()).userDao }
        }
    }
}