package com.stephen.jetpack.data.compat

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * create by stephen
 * on 2020/5/14
 */

class ListDataSourceFactory<T>(
    private val remoteData:(page: Int) ->  Observable<List<T>>,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, T>() {
    val listMutableList = MutableLiveData<ListDataSource<T>>()
    override fun create(): DataSource<Int, T> {
        val listDataSource = ListDataSource(remoteData,compositeDisposable)
        listMutableList.postValue(listDataSource)
        return listDataSource
    }

}