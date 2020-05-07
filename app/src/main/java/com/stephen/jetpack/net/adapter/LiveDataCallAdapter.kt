package com.stephen.jetpack.net.adapter

import androidx.lifecycle.LiveData
import com.stephen.common.bean.BaseResp
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * create by stephen
 * on 2020/5/7
 */


class LiveDataCallAdapter<T> (private val responseType:Type) :CallAdapter<T,LiveData<T>>{
    override fun adapt(call: Call<T>): LiveData<T> {
        return object :LiveData<T>(){
            override fun onActive() {
                super.onActive()
                val started = AtomicBoolean(false)
                if (started.compareAndSet(false, true)) {//确保执行一次
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            val value = BaseResp<T>(1, -1, 10,10,null) as T
                            postValue(value)
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            postValue(response.body())
                        }
                    })
                }
            }
        }
    }

    override fun responseType(): Type = responseType
}