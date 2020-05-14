package com.stephen.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.stephen.common.ui.BaseActivity
import com.stephen.jetpack.databinding.ActivityMainBinding
import com.stephen.jetpack.ui.dashboard.DashboardFragment
import com.stephen.jetpack.ui.home.HomeFragment
import com.stephen.jetpack.ui.notifications.NotificationsFragment
import com.stephen.jetpack.view.TabItem
import com.stephen.jetpack.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.layout_menu.view.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private val tabs = arrayOf(
        TabItem(R.drawable.tab_home, "首页", HomeFragment::class.java),
        TabItem(R.drawable.tab_project, "项目", DashboardFragment::class.java),
        TabItem(R.drawable.tab_wx, "公众号", NotificationsFragment::class.java)
    )
    private val fragments = ArrayList<Fragment>()

    /**
     * fragment重叠
     */
    override fun onSaveInstanceState(outState: Bundle) {
        fragments.forEach {
            supportFragmentManager.putFragment(
                outState,
                it.javaClass.simpleName,
                it
            )
        }
        super.onSaveInstanceState(outState)
    }

    private fun initFragments() {
        if (fragments.isEmpty()) {
            tabs.forEach {
                val f = it.cls.newInstance()
                fragments.add(f)
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        fragments.forEach {
            if (!it.isAdded) transaction.add(
                R.id.fl_content, it, it.javaClass
                    .simpleName
            ).hide(it)
        }
        transaction.commit()
    }

    private fun initTabLayout() {
        mbinding.tabLayout.run {
            tabs.forEach {
                addTab(newTab().setCustomView(getTabView(it)))
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) {}

                override fun onTabUnselected(p0: TabLayout.Tab?) {}

                override fun onTabSelected(p0: TabLayout.Tab) {
                    initTab(p0.position)
                }
            })
            getTabAt(0)?.select()
        }
        initTab(0)
    }

    private fun getTabView(it: TabItem): View {
        val v = LayoutInflater.from(this).inflate(R.layout.layout_menu,null)
        v.iv_tab.setImageResource(it.resId)
        v.tv_name.text = it.title
        return v
    }

    private fun initTab(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        fragments.forEachIndexed { index, fragment ->
            if (index == position) {
                transaction.show(fragment)
            } else {
                transaction.hide(fragment)
            }
        }
        transaction.commit()
    }

    override fun initData() {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        initFragments()
        initTabLayout()
    }
}
