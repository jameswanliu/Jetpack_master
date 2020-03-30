package com.stephen.jetpack

import com.stephen.common.ui.BaseActivity
import com.stephen.jetpack.databinding.ActivitySplashBinding

class SplashActivity :BaseActivity<ActivitySplashBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
    }
}