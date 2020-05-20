package com.stephen.jetpack.data.compat

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.stephen.common.log.logger
import com.stephen.jetpack.net.status.NetworkStatus
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

/**
 * create by stephen
 * on 2020/5/9
 */
class ListDataSource<T>(
    var remoteData: (page: Int) -> Observable<List<T>>,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, T>() {
    val networkState = MutableLiveData<NetworkStatus>()
    val initialLoad = MutableLiveData<NetworkStatus>()
    val refreshComplete = MutableLiveData<Boolean>()
    val newDataArrive = SingleLiveEvent<Void>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        networkState.postValue(NetworkStatus.LOADING)
        initialLoad.postValue(NetworkStatus.LOADING)
        setRetry(null)
        compositeDisposable.add(remoteData.invoke(1).subscribe({ items ->
            setRetry(null)//
            networkState.postValue(NetworkStatus.LOADED)
            initialLoad.postValue(NetworkStatus.LOADED)
            refreshComplete.postValue(items.size == 10)
            callback.onResult(items, null, 1)
            newDataArrive.postCall()
        }, { throwable ->
            newDataArrive.postCall()
            setRetry(Action { loadInitial(params, callback) })
            val error = NetworkStatus.error(throwable)
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        networkState.postValue(NetworkStatus.LOADING)
        compositeDisposable.add(remoteData.invoke(params.key).subscribe({ items ->
            setRetry(null)
            networkState.postValue(NetworkStatus.LOADED)
            refreshComplete.postValue(items.size == 10)
            callback.onResult(items, params.key + 1)
            newDataArrive.postCall()
        }, { throwable ->
            setRetry(Action { loadAfter(params, callback) })
            networkState.postValue(NetworkStatus.error(throwable))
        }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
//        networkState.postValue(NetworkStatus.LOADING)
//        compositeDisposable.add(remoteData.invoke(params.key).subscribe({ items ->
//            setRetry(null)
//            networkState.postValue(NetworkStatus.LOADED)
//            callback.onResult(items, params.key - 1)
//            newDataArrive.postCall()
//        }, { throwable ->
//            setRetry(Action { loadAfter(params, callback) })
//            networkState.postValue(NetworkStatus.error(throwable))
//        }))
    }

    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribe({ }, { logger.info("retry error =${it.message}") })
            )
        }
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
}