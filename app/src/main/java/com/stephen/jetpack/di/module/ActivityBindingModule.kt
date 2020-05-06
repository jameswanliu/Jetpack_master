package com.stephen.jetpack.di.module

import com.stephen.jetpack.di.ActivityScoped
import com.stephen.jetpack.MainActivity
import com.stephen.jetpack.SplashActivity
import com.stephen.jetpack.di.module.activity.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun bindMainActivity(): MainActivity

}