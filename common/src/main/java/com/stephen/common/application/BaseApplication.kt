package com.stephen.common.application

import android.app.Application
import android.content.ContextWrapper

private lateinit var INSTANCE: BaseApplication
abstract class BaseApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

}
object applicationContext : ContextWrapper(INSTANCE)