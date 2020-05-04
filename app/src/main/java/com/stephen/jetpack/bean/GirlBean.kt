package com.stephen.jetpack.bean

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
)