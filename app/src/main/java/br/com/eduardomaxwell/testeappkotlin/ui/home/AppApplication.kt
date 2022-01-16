package br.com.eduardomaxwell.testeappkotlin.ui.home

import android.app.Application
import br.com.eduardomaxwell.testeappkotlin.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            AppModules.load()
        }
    }
}