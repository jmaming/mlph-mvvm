package com.mlph.mvvm_android.utils.session

import io.reactivex.Completable
import io.reactivex.Single

interface UserSession {

    /**
     * Determine if there's currently stored user session.
     * This means that we have an unexpired stored API Token on our {@link SharedPreferences}.
     */
    fun hasExistingUserSession() : Single<Boolean>

    /**
     * Retrieve the currently cached user data.
     *
     * @return {@link Single} that emits {@link User} which contains information regarding the
     * current user.
     */
//    fun getCurrentUser() : Single<Profile>

    /**
     * Retrieve the API Token associated with the currently logged in user.
     * */
    fun getToken() : Single<String>

    /**
     * This will remove the current user from SharedPreference whenever the user logs out from the
     * app or the user session has expired.
     * */
    fun removeCurrentUser() : Completable

}