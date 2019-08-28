package com.mlph.mvvm_android.ui.main

import com.mlph.mvvm_android.ui.main.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun providesMainViewModel() : MainViewModel {
        return MainViewModel()
    }

}