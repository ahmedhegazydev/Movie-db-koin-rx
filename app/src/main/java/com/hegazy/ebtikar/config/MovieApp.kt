package com.hegazy.ebtikar.config

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.hegazy.ebtikar.BuildConfig
import com.hegazy.ebtikar.koin.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

open class MovieApp : Application() {

    companion object {
        var instance: MovieApp? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        context = applicationContext

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@MovieApp)
            modules(provideDependency())
        }

    }

    open fun provideDependency() = appComponent

}