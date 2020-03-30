package com.stephen.jetpack.utils

import android.content.Context
import android.os.Build
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import javax.inject.Inject


class SystemUtil @Inject constructor() {

    fun clearCookies(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null)
            CookieManager.getInstance().flush()
        } else {
            val cookieSyncMngr = CookieSyncManager.createInstance(context)
            cookieSyncMngr.startSync()
            val cookieManager = CookieManager.getInstance()
            cookieManager.removeAllCookie()
            cookieManager.removeSessionCookie()
            cookieSyncMngr.stopSync()
            cookieSyncMngr.sync()
        }
    }
}