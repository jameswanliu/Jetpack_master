package com.stephen.common.utils

import com.stephen.common.sharedpreferences.Preference
import com.stephen.common.application.applicationContext


object Settings {
    var language: String by Preference(applicationContext, "lang", "cn")
    var token: String by Preference(applicationContext, "tokenkey", "")
    var userId: String by Preference(applicationContext, "userId", "")
    var stateBarHeight: Int by Preference(applicationContext, "stateBarHeight", 40)
    var lanuageType:Int by Preference(applicationContext,"languageType",0)

}