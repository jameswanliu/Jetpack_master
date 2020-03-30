package com.stephen.jetpack.application

import android.content.Context
import com.stephen.common.application.BaseApplication
import com.stephen.jetpack.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * create by stephen
 * on 2020/3/30
 */

class DDApplication : BaseApplication() {

    private lateinit var androidInjector: AndroidInjector<out DaggerApplication>
    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        androidInjector = DaggerAppComponent.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = androidInjector

}