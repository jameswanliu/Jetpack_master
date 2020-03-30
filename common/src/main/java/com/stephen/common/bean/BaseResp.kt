package com.stephen.common.bean

import java.io.Serializable


class BaseResp<T>(val code: Int, val status: String, val msg: String, val time: Int,val bcxgame_id:String,val data: T):Serializable