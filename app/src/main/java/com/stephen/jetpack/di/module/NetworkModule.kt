package com.stephen.jetpack.di.module

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.stephen.jetpack.net.ApiService
import com.stephen.jetpack.net.adapter.LiveDataCallAdapterFactory
import com.stephen.jetpack.utils.Memory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    companion object {
        private const val TIME_OUT = 40L
        private const val DATA_URL = "https://gank.io/api/"
    }

    private fun buildOkHttpClient(application: Application): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .cache(
                Cache(
                    File(application.cacheDir, "stephencache"),
                    Memory.calcCacheSize(application, .25f)
                )
            )
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient = buildOkHttpClient(application)

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl(DATA_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
        .build()
        .create(ApiService::class.java)
}
