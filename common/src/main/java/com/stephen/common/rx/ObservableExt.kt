package com.stephen.common.rx

import com.stephen.common.bean.BaseResp
import io.reactivex.Observable

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFun())
}
