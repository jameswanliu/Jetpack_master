package com.stephen.common.rx

import com.stephen.common.bean.BaseResp
import com.stephen.common.ext.otherwise
import com.stephen.common.ext.yes
import io.reactivex.Observable
import io.reactivex.functions.Function


class BaseFun<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        (t.code == 10000).yes {
            return Observable.just(t.data)
        }.otherwise {
            return Observable.error(BaseException(t.code, t.msg))
        }
    }

}