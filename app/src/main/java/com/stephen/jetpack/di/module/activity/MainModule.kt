package com.stephen.jetpack.di.module.activity

import android.app.Activity
import com.ddzh.downloader.di.ActivityScoped
import com.stephen.jetpack.MainActivity
import com.stephen.jetpack.utils.fragment.FragmentFactory
import com.stephen.jetpack.utils.fragment.FragmentFactoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {


    @ActivityScoped
    @Binds
    abstract fun bindFragmentFactory(fragmentFactory: FragmentFactoryImpl): FragmentFactory


    @ActivityScoped
    @Binds
    abstract fun bindMainActivity(mainActivity: MainActivity): Activity
}