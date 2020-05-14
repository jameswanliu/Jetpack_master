package com.stephen.jetpack.net.exception

import com.stephen.jetpack.net.API_CODE_DATA_ERROR


/**
 * 未知或未处理的异常
 */

class DataErrorException : ApiException {

    constructor(message: String) : super(message, API_CODE_DATA_ERROR)

    constructor(message: String, e: Exception) : super(message, API_CODE_DATA_ERROR, e)
}
