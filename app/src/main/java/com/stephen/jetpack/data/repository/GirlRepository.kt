package com.stephen.jetpack.data.repository

import androidx.lifecycle.LiveData
import com.stephen.common.bean.BaseResp
import com.stephen.jetpack.bean.GirlBean
import com.stephen.jetpack.di.qualifier.RemoteData
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * create by stephen
 * on 2020/5/6
 */


interface GirlRepository {
    fun getGirlDataList(position: Int):  LiveData<BaseResp<List<GirlBean>>>
}

@Singleton
class GirlRepositoryImpl @Inject constructor(@RemoteData private val girlRemoteDataSource: GirlRepository) : GirlRepository {
    override fun getGirlDataList(position: Int):  LiveData<BaseResp<List<GirlBean>>>  =
        girlRemoteDataSource.getGirlDataList(position)
}