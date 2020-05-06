package com.stephen.jetpack

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.stephen.common.ui.BaseActivity
import com.stephen.jetpack.databinding.ActivityMainBinding
import com.stephen.jetpack.ui.home.HomeViewModel
import com.stephen.jetpack.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {



    private lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        mainViewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.onStart()
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }


    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.onStop()
    }
}
