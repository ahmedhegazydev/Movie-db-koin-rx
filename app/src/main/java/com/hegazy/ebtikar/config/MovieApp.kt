package com.hegazy.ebtikar.config

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.hegazy.ebtikar.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApp : Application() {

    companion object {
        var instance: MovieApp? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        context = applicationContext


        startKoin {
            androidLogger()
            androidContext(context)
            modules(viewModelModule)
        }

    }


}