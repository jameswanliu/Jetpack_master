package com.stephen.jetpack.di.module

import com.stephen.jetpack.data.remote.GirlRemoteDataSource
import com.stephen.jetpack.data.repository.GirlRepository
import com.stephen.jetpack.data.repository.GirlRepositoryImpl
import com.stephen.jetpack.di.qualifier.RemoteData
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    @RemoteData
    abstract fun bindGirlRemoteDataSource(remoteDataSource: GirlRemoteDataSource): GirlRepository


    @Singleton
    @Binds
    abstract fun bindGirlRepositoryImpl(girlRepositoryImpl: GirlRepositoryImpl): GirlRepository
}