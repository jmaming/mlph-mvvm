package com.mlph.mvvm_android.utils.session

import dagger.Binds
import dagger.Module

@Module
interface UserSessionModule {

    @Binds
    fun bindUserSession(userSessionImpl: UserSessionImpl) : UserSession

}