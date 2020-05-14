package com.stephen.jetpack.net.exception

import com.stephen.common.rx.OnError


/**
 * 服务器给返回的异常
 */

open class ApiException : RuntimeException {

    var serverCode: Int = 0
        private set

    constructor(message: String, serverCode: Int) : super(message) {
        this.serverCode = serverCode
    }

    constructor(message: String, serverCode: Int, e: Exception) : super(message, e) {
        this.serverCode = serverCode
    }


       public fun trans(error: OnError){
            error.onError(serverCode, message!!)
        }

}