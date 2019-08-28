package com.mlph.mvvm_android.api

import com.mlph.mvvm_android.api.ApiFactory
import com.mlph.mvvm_android.api.ApiFactoryImpl
import dagger.Binds
import dagger.Module

@Module
interface ApiModule {

    @Binds
    fun bindApiFactory(apiFactoryImpl: ApiFactoryImpl): ApiFactory

}