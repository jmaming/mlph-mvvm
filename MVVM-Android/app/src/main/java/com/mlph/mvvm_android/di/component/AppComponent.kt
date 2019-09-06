package com.mlph.mvvm_android.di.component

import com.mlph.mvvm_android.BaseApplication
import com.mlph.mvvm_android.api.ApiModule
import com.mlph.mvvm_android.di.ActivityBuilder
import com.mlph.mvvm_android.di.module.AppModule
import com.mlph.mvvm_android.utils.prefs.PrefsUtilModule
import com.mlph.mvvm_android.utils.rx.RxUtilityModule
import com.mlph.mvvm_android.utils.session.UserSessionModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    RxUtilityModule::class,
    ApiModule::class,
    PrefsUtilModule::class,
    UserSessionModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(mvvmApplication: BaseApplication) : Builder

        fun build() : AppComponent
    }

    fun inject(app: BaseApplication)

}