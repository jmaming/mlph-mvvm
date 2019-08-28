package com.mlph.mvvm_android.di.module

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.mlph.mvvm_android.MVVMApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApp(app: MVVMApplication) : Application {
        return app
    }

    @Provides
    @Singleton
    fun provideContext(app: MVVMApplication): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context) : ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

}