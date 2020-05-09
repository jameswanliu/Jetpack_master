package com.stephen.jetpack.data.compat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.stephen.jetpack.net.status.NetworkStatus

/**
 * create by stephen
 * on 2020/5/9
 */
class ListDataSource <T>(val remoteData:(page:Int)->LiveData<PagedList<T>>
): PageKeyedDataSource<Int, T>() {
    val networkState = MutableLiveData<NetworkStatus>()
    val initialLoad = MutableLiveData<NetworkStatus>()
    val newDataArrive = SingleLiveEvent<Void>()



    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
        networkState.postValue(NetworkStatus.LOADING)
        initialLoad.postValue(NetworkStatus.LOADING)
                    setRetry(null)
        remoteData.invoke(1).observe()
                    networkState.postValue(NetworkStatus.LOADED)
                    initialLoad.postValue(NetworkStatus.LOADED)
                    callback.onResult(remoteData., null, 2)
                    newDataArrive.postCall()
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        // set network value to loading.
        networkState.postValue(NetworkStatus.LOADING)

        //get the users from the api after id
        compositeDisposable.add(NetworkStatus.invoke(params.key).subscribe({ items ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkStatus.LOADED)
            callback.onResult(items, params.key+1)
            newDataArrive.postCall()
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadAfter(params, callback) })
            // publish the error
            networkState.postValue(NetworkState.error(throwable))
        }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        // set network value to loading.
        networkState.postValue(NetworkState.LOADING)

        //get the users from the api after id
        compositeDisposable.add(remoteData.invoke(params.key).subscribe({ items ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(items, params.key-1)
            newDataArrive.postCall()
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadAfter(params, callback) })
            // publish the error
            networkState.postValue(NetworkState.error(throwable))
        }))
    }

    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, { throwable -> Log.e("ListDataSource",throwable.message) }))
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