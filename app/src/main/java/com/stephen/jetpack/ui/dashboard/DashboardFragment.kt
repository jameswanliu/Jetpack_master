package com.stephen.jetpack.ui.dashboard

import com.stephen.jetpack.base.BaseViewModelFragment
import com.stephen.jetpack.R
import com.stephen.jetpack.databinding.FragmentDashboardBinding

class DashboardFragment : BaseViewModelFragment<FragmentDashboardBinding, DashboardViewModel>() {
    companion object{
        fun newInstance():DashboardFragment{
            return DashboardFragment()
        }
    }

//    override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View? {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root
//    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_dashboard
    }

    override fun initData() {
    }


}
