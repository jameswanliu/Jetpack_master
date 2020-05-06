package com.stephen.jetpack.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stephen.jetpack.di.ViewModelKey
import com.stephen.jetpack.ui.dashboard.DashboardViewModel
import com.stephen.jetpack.ui.home.HomeViewModel
import com.stephen.jetpack.ui.notifications.NotificationsViewModel
import com.stephen.jetpack.viewmodel.MainViewModel
import com.stephen.jetpack.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * create by stephen
 * on 2020/5/4
 */


@Module(includes = [AppModule::class])
abstract class ViewModelModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(model: NotificationsViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(model: HomeViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(model: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(model: DashboardViewModel): ViewModel

}