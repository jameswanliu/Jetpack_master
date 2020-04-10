package com.stephen.jetpack.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.hjq.toast.ToastUtils
import com.stephen.jetpack.R
import com.stephen.jetpack.adapter.CommomAdapter
import com.stephen.jetpack.databinding.ActivityCompatListBinding
import com.stephen.jetpack.view.ErrorInfoView
import com.stephen.jetpack.viewmodel.CommonListViewModel
import kotlinx.android.synthetic.main.activity_compat_list.*
import kotlinx.android.synthetic.main.layout_commonlist_header.*
import org.jetbrains.anko.sdk15.listeners.onClick

abstract class CommonListActivity<Data, viewModel : CommonListViewModel<Data>> :
    BaseViewModelActivity<viewModel, ActivityCompatListBinding>() {

    abstract val adapter: CommomAdapter<Data>
    private lateinit var recyclerViewAdapter: LRecyclerViewAdapter
    abstract val mViewModels: viewModel
    val PAGE_SIZE = 20

    protected val errorInfoView by lazy {
        ErrorInfoView(rootView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compat_list)
        initRecyclview()
        tv_title.text = setTitle()
        ib_back.onClick {
            finish()
        }
    }

    abstract fun setTitle(): String


    fun loadMoreEnable(data: List<Data>) = recyclerView.setLoadMoreEnabled(data.size >= PAGE_SIZE)

    fun loadMoreEnable(boolean: Boolean = false) = recyclerView.setLoadMoreEnabled(boolean)

    private fun initRecyclview() {
        recyclerViewAdapter = LRecyclerViewAdapter(adapter)
        recyclerViewAdapter.setOnItemClickListener { view, position -> onItemClick(view, position) }
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setLoadMoreEnabled(true)
        recyclerView.setOnLoadMoreListener { mViewModels.loadMore() }
        recyclerView.setOnRefreshListener { mViewModels.refreshData() }
    }


    protected open fun onItemClick(view: View, position: Int) = Unit

    fun setLoadMoreEnable(isEnabled: Boolean) = recyclerView.setLoadMoreEnabled(isEnabled)


    protected fun addHeaderView(view: View) {
        recyclerViewAdapter.addHeaderView(view)
    }

    protected fun addView(index: Int, view: View) {
        rootView.addView(view, index)
    }

    protected fun addFooterView(view: View) {
        recyclerViewAdapter.addFooterView(view)
    }

    fun onDataInit(data: ArrayList<Data>) {
        adapter.data.clear()
        adapter.data.addAll(data)
        recyclerView.setNoMore(data.size < PAGE_SIZE)
        onRefeshComplete()
        recyclerView.refresh()
        dissmissErrorView()
    }


    fun onRefeshComplete(pageSize: Int = PAGE_SIZE) = recyclerView.refreshComplete(pageSize)

    fun onDataRefresh(data: ArrayList<Data>) = onDataInit(data)

    fun onDataInitWithNothing() {
        showErrorView(resources.getString(R.string.no_data))
        recyclerView.setNoMore(true)
        recyclerView.refreshComplete(PAGE_SIZE)
        errorInfoView.isClickable = false
    }

    fun onDataInitWithError(error: String) {
        showErrorView(error)
        errorInfoView.onClick {
            mViewModels.initData()
        }
    }

    fun onDataRefreshWithError(error: String) {
        if (adapter.data.isEmpty()) {
            showErrorView(error)
            errorInfoView.onClick {
                mViewModels.initData()
            }
        } else {
            ToastUtils.show(error)
        }
    }

    fun onMoreDataLoaded(data: ArrayList<Data>) {
        adapter.data.update(data)
        recyclerView.refreshComplete(PAGE_SIZE)
        recyclerView.setNoMore(data.size < PAGE_SIZE)
        dissmissErrorView()
    }

    fun onMoreDataLoadedWithError() {
        recyclerView.refreshComplete(PAGE_SIZE)
        errorInfoView.onClick {
            mViewModels.initData()
        }
    }


    protected fun showErrorView(msg: String) = errorInfoView.show(msg)


    protected fun dissmissErrorView() = errorInfoView.dismiss()

}