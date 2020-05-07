package com.stephen.jetpack.net.adapter

import androidx.lifecycle.LiveData
import com.stephen.common.bean.BaseResp
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * create by stephen
 * on 2020/5/7
 */

class LiveDataCallAdapterFactory : CallAdapter.Factory(){


    companion object{
        fun create():LiveDataCallAdapterFactory = LiveDataCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) return null
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(observableType)
        if (rawType != BaseResp::class.java) {
            throw IllegalArgumentException("type must be BaseResp")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        return LiveDataCallAdapter<Any>(observableType)
    }


}