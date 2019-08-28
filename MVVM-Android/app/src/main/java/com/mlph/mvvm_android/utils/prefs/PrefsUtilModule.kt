package com.mlph.mvvm_android.utils.prefs

import dagger.Binds
import dagger.Module

@Module
interface PrefsUtilModule {

    @Binds
    fun bindPrefsUtil(prefsUtilImpl: PrefsUtilImpl) : PrefsUtil

}