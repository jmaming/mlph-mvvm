package com.mlph.mvvm_android.api

import androidx.annotation.VisibleForTesting

class SessionExpiredException() : Throwable(ERROR_MESSAGE) {

    companion object {
        @VisibleForTesting
        const val ERROR_MESSAGE = "Session has expired"
    }

}