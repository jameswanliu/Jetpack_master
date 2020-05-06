package com.stephen.jetpack.di.component

import com.stephen.jetpack.application.DDApplication
import com.stephen.jetpack.di.module.*
import com.stephen.jetpack.di.module.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class, UtilModule::class,
        DatabaseModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<DDApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DDApplication): Builder

        fun build(): AppComponent
    }
}