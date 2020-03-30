package com.stephen.jetpack.di.component

import com.ddzh.downloader.di.module.*
import com.stephen.common.application.BaseApplication
import com.stephen.jetpack.di.module.*
import com.stephen.jetpack.di.module.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by cuongpm on 12/6/18.
 */

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class, UtilModule::class,
        DatabaseModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): Builder
        fun build(): AppComponent
    }
}