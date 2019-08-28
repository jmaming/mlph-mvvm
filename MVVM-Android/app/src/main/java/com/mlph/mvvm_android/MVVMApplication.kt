package com.mlph.mvvm_android

import android.app.Activity
import android.app.Application
import android.app.Service
import androidx.fragment.app.Fragment
import com.mlph.mvvm_android.base.BaseActivity
import com.mlph.mvvm_android.di.component.AppComponent
import com.mlph.mvvm_android.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MVVMApplication : Application(), HasActivityInjector, HasServiceInjector, HasSupportFragmentInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private var currentActivity: BaseActivity<*, *>? = null

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }

    fun appComponent() : AppComponent = appComponent

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    fun getCurrentActivity() : BaseActivity<*, *>? {
        return currentActivity
    }

    fun setCurrentActivity(currentActivity: BaseActivity<*, *>?) {
        this.currentActivity = currentActivity
    }

}