package com.stephen.jetpack.bean

import java.io.Serializable

/**
 * create by stephen
 * on 2020/5/4
 */


data class GirlBean(
    val id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCounts: Int,
    val url: String
) : Serializable

//"page":1,"page_count":8,"status":100,"total_counts":77
data class GirlResp(
    val data: GirlBean,
    val page: Int,
    val page_count: Int,
    val status: Int,
    val total_counts: Int
) : Serializable