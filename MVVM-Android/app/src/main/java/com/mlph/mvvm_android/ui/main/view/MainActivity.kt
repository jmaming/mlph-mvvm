package com.mlph.mvvm_android.ui.main.view

import android.os.Bundle
import com.mlph.mvvm_android.BR
import com.mlph.mvvm_android.R
import com.mlph.mvvm_android.base.BaseActivity
import com.mlph.mvvm_android.databinding.ActivityMainBinding
import com.mlph.mvvm_android.ui.main.viewmodel.MainViewModel

import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = getViewDataBinding()
        activityMainBinding.viewModel = mainViewModel
    }
}
