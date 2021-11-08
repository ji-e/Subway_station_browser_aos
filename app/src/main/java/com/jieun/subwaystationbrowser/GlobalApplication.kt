package com.jieun.subwaystationbrowser

import android.app.Activity
import android.app.Application
import com.jieun.subwaystationbrowser.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

/**
 * date 2021-11-07
 * create by jieun
 */
class GlobalApplication : Application() {
    companion object {
        lateinit var instance: GlobalApplication
        lateinit var baseActivity: Activity

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@GlobalApplication)
            modules(
                listOf(
                    apiModule,
                    repositoryModule,
                    dataSourceModule,
                    viewModelModule
                )
            )
            logger(
                if (BuildConfig.DEBUG) {
                    AndroidLogger()
                } else {
                    EmptyLogger()
                }
            )
        }
    }
}