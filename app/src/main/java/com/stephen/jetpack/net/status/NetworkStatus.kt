package com.stephen.jetpack.net.status

import io.reactivex.Observable

/**
 * create by stephen
 * on 2020/5/9
 */


enum class Status {
    FAILED,
    SUCCESS,
    LOADING
}

data class NetworkStatus private constructor(val status: Status, val throwable: Throwable? = null) {
    companion object {
        val LOADED = NetworkStatus(Status.SUCCESS)
        val LOADING = NetworkStatus(Status.LOADING)
        fun error(throwable: Throwable?) = NetworkStatus(Status.FAILED, throwable)


    }
}