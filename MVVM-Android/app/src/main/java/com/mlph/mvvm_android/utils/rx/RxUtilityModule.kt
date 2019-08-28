package com.mlph.mvvm_android.utils.rx

import com.mlph.mvvm_android.utils.rx.internet.RxInternet
import com.mlph.mvvm_android.utils.rx.internet.RxInternetImpl
import com.mlph.mvvm_android.utils.rx.scheduler.RxScheduler
import com.mlph.mvvm_android.utils.rx.scheduler.RxSchedulerImpl
import dagger.Binds
import dagger.Module

@Module
interface RxUtilityModule {

    @Binds
    fun bindRxSchedule(rxSchedulerImpl: RxSchedulerImpl) : RxScheduler

    @Binds
    fun bindsRxInternet(rxInternetImpl: RxInternetImpl) : RxInternet

//    @Binds
//    fun bindsRxErrorParser(rxErrorParserImpl: RxErrorParserImpl) : RxErrorParser

}