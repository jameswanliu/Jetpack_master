package com.stephen.common.bean

import java.io.Serializable

//"page":1,"page_count":8,"status":100,"total_counts":77
class BaseResp<T>(val page: Int, val status: Int, val page_count: Int,val total_counts:Int,val data: T?):Serializable