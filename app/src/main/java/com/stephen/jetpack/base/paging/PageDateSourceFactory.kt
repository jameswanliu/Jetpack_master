package com.stephen.jetpack.base.paging

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource


/**
 * create by stephen
 * on 2020/5/4
 */


class PageDateSourceFactory<T>(private val positionalDataSource: PositionalDataSource<T>) : DataSource.Factory<Int,T>(){
    override fun create(): DataSource<Int, T> = positionalDataSource
}