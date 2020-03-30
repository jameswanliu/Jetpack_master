package com.stephen.jetpack.di.module

import androidx.room.Room
import com.stephen.common.application.BaseApplication
import com.stephen.jetpack.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: BaseApplication): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "stephen.db").build()
    }
}