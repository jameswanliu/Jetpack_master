package com.stephen.jetpack.di.module.activity

import android.app.Activity
import com.stephen.jetpack.di.ActivityScoped
import com.stephen.jetpack.MainActivity
import com.stephen.jetpack.di.FragmentScoped
import com.stephen.jetpack.ui.dashboard.DashboardFragment
import com.stephen.jetpack.ui.home.HomeFragment
import com.stephen.jetpack.ui.notifications.NotificationsFragment
import com.stephen.jetpack.utils.fragment.FragmentFactory
import com.stephen.jetpack.utils.fragment.FragmentFactoryImpl
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ActivityScoped
    @Binds
    abstract fun bindFragmentFactory(fragmentFactory: FragmentFactoryImpl): FragmentFactory

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindDashboardFragment(): DashboardFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindNotificationsFragment(): NotificationsFragment

    @ActivityScoped
    @Binds
    abstract fun bindMainActivity(mainActivity: MainActivity): Activity
}