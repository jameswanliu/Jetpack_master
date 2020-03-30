package com.stephen.jetpack.di.module.activity

import com.ddzh.downloader.di.ActivityScoped
import com.stephen.jetpack.utils.fragment.FragmentFactory
import com.stephen.jetpack.utils.fragment.FragmentFactoryImpl
import dagger.Binds
import dagger.Module

/**
 * Created by cuongpm on 12/9/18.
 */

@Module
abstract class MainModule {


    @ActivityScoped
    @Binds
    abstract fun bindFragmentFactory(fragmentFactory: FragmentFactoryImpl): FragmentFactory
}