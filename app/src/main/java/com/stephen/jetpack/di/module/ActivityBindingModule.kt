package com.stephen.jetpack.di.module

import com.ddzh.downloader.di.ActivityScoped
import com.stephen.jetpack.MainActivity
import com.stephen.jetpack.SplashActivity
import com.stephen.jetpack.di.module.activity.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cuongpm on 12/6/18.
 */

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun bindMainActivity(): MainActivity

}