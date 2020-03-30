package com.stephen.common.application

import android.app.Application
import android.content.ContextWrapper
import dagger.android.support.DaggerApplication

private lateinit var INSTANCE: BaseApplication
abstract class BaseApplication : DaggerApplication(){
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}
object applicationContext : ContextWrapper(INSTANCE)