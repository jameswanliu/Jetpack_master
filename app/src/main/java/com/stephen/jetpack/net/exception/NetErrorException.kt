package com.stephen.jetpack.net.exception

import com.stephen.jetpack.net.API_CODE_NET_ERROR


/**
 * 网络链接失败的异常
 */

class NetErrorException : ApiException {

    constructor(message: String) : super(message, API_CODE_NET_ERROR)

    constructor(message: String, e: Exception) : super(message, API_CODE_NET_ERROR, e)
}
