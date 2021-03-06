package com.stephen.jetpack.net
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.stephen.common.bean.BaseResp
import com.stephen.jetpack.bean.GirlBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v2/data/category/Girl/type/Girl/page/1/count/{count}")
    fun getGirlsList(@Path("count") count: Int): Observable<BaseResp<List<GirlBean>>>
}