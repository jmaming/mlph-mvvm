package com.mlph.mvvm_android.utils.session

import com.mlph.mvvm_android.utils.prefs.PrefsKey
import com.mlph.mvvm_android.utils.prefs.PrefsUtil
import com.mlph.mvvm_android.utils.session.UserSession
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserSessionImpl @Inject constructor(private var prefsUtil: PrefsUtil) : UserSession {

    override fun hasExistingUserSession(): Single<Boolean> {
        return prefsUtil
            .getString(PrefsKey.TOKEN)
            .flatMap {
                Single.just(true)
            }
            .onErrorReturnItem(false)
    }

//    override fun getCurrentUser(): Single<Profile> {
//        return prefsUtil
//            .convertJsonStringToObject(PrefsKey.USER_DATA, LoginResponseDto::class.java)
//            .flatMap(this::parseUser)
//    }

    override fun getToken(): Single<String> {
        return prefsUtil.getString(PrefsKey.TOKEN)
    }

    override fun removeCurrentUser(): Completable {
        return prefsUtil.clearAll()
    }

//    private fun parseUser(loginResponseDto: LoginResponseDto) : Single<Profile> {
//        return Single.just(loginResponseDto)
//            .compose(DtoToLoginResponseMapper())
//            .flatMap { loginResponse ->
//                Single.just(loginResponse.getProfile())
//            }
//    }
}