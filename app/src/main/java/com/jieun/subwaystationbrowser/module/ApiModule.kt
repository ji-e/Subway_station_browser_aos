package com.jieun.subwaystationbrowser.module

import com.jieun.subwaystationbrowser.network.ApiService
import com.jieun.subwaystationbrowser.network.NetworkConstants
import com.jieun.subwaystationbrowser.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * date 2021-11-07
 * create by jieun
 */
val apiModule: Module = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(NetworkConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkConstants.BASE_URL)
            .build()
    }

    single {
        get<Retrofit>().create(
            ApiService::class.java
        )
    }
}
