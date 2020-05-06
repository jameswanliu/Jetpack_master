package com.stephen.jetpack.net
import androidx.paging.PagedList
import com.stephen.jetpack.bean.GirlBean
import com.stephen.jetpack.bean.GirlResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v2/data/category/Girl/type/Girl/page/1/count/{count}")
    fun getGirlsList(@Path("count") count: Int): Observable<GirlResp>
}