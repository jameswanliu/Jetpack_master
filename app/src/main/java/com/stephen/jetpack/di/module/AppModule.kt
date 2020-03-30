package com.stephen.jetpack.di.module

import android.app.Application
import android.content.Context
import com.ddzh.downloader.di.qualifier.ApplicationContext
import com.stephen.common.utils.scheduler.BaseSchedulers
import com.stephen.common.utils.scheduler.BaseSchedulersImpl
import com.stephen.common.application.BaseApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by cuongpm on 12/6/18.
 */

@Module
abstract class AppModule {

    @Binds
    @ApplicationContext
    abstract fun bindApplicationContext(application: BaseApplication): Context

    @Binds
    abstract fun bindApplication(application: BaseApplication): Application

    @Singleton
    @Binds
    abstract fun bindBaseSchedulers(baseSchedulers: BaseSchedulersImpl): BaseSchedulers
}