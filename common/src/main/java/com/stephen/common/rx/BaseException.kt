package com.stephen.common.rx

class BaseException(val code:Int,val msg:String):Throwable(msg) {
}