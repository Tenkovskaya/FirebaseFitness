package com.tenkovskaya.fitnes.presentation

import android.app.Application
import com.google.firebase.FirebaseApp
import com.tenkovskaya.fitnes.di.appModuleDatabase
import com.tenkovskaya.fitnes.di.appModuleFactory
import com.tenkovskaya.fitnes.di.appModuleSingle
import com.tenkovskaya.fitnes.di.appModuleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(this@MainApplication)
            modules(appModuleSingle, appModuleFactory, appModuleViewModel, appModuleDatabase)
        }
    }
}