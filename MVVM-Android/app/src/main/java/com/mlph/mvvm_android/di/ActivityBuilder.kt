package com.mlph.mvvm_android.di

import com.mlph.mvvm_android.ui.main.MainActivityModule
import com.mlph.mvvm_android.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        MainActivityModule::class
    ])
    abstract fun bindsMainActivity() : MainActivity

}