package com.mlph.mvvm_android.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class BaseFragmentPagerAdapter(fm: FragmentManager, private var fragments: ArrayList<Fragment>) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

}