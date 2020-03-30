package com.ddzh.downloader.di.module

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import com.stephen.jetpack.utils.AppUtil
import com.ddzh.downloader.util.IntentUtil
import com.stephen.jetpack.utils.FileUtil
import com.stephen.jetpack.utils.SystemUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UtilModule {

    @Singleton
    @Provides
    fun bindDownloadManager(application: Application): DownloadManager =
        application.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    @Singleton
    @Provides
    fun bindFileUtil() = FileUtil()

    @Singleton
    @Provides
    fun bindSystemUtil() = SystemUtil()

    @Singleton
    @Provides
    fun bindIntentUtil() = IntentUtil()

    @Singleton
    @Provides
    fun bindAppUtil() = AppUtil()
}