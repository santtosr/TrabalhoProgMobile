package com.example.trabalho2progmobile.utils.mvvm

import android.app.Application
import com.example.trabalho2progmobile.utils.mvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Trabalho2ProgMobile: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Trabalho2ProgMobile)
            modules(viewModelModule)
        }
    }
}