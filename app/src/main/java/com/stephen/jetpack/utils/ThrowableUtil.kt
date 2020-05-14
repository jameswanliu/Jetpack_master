package com.stephen.jetpack.utils


import com.stephen.jetpack.net.exception.DataErrorException
import com.stephen.jetpack.net.exception.ApiException
import com.stephen.common.log.logger
import com.stephen.common.utils.CharacterUtil
import com.stephen.jetpack.net.API_CODE_EMPTY
import com.stephen.jetpack.net.API_CODE_NET_ERROR

fun Throwable.convert(): ApiException {
    if (this is ApiException) {
        return this
    }
    var msg = if (message.isNullOrBlank()) {
        ""
    } else {
        message
    }
    logger.error("接口异常")
    msg = if (msg!!.length <= 50 && CharacterUtil.containChinese(msg)) msg else "获取数据失败"
    return DataErrorException(msg)
}

fun Throwable.netError(): Boolean {
    return if (this is ApiException) {
        this.serverCode == API_CODE_NET_ERROR
    } else {
        false
    }
}

fun Throwable.noData(): Boolean {
    return if (this is ApiException) {
        this.serverCode == API_CODE_EMPTY
    } else {
        false
    }
}
