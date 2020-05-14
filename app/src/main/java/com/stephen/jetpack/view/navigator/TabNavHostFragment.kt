package com.stephen.jetpack.view.navigator

import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment

/**
 * create by stephen
 * on 2020/5/14
 */
class TabNavHostFragment : NavHostFragment() {
    override fun createFragmentNavigator(): Navigator<out FragmentNavigator.Destination> =
        MyNavigator(requireContext(), childFragmentManager, id)
}
