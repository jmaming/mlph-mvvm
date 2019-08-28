package com.mlph.mvvm_android.utils.rx.internet

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.mlph.mvvm_android.R

class NoInternetConnectionException : Throwable {

    companion object {
        @VisibleForTesting
        const val ERROR_MESSAGE = "No internet connection"
    }

    constructor() : super(ERROR_MESSAGE)

    constructor(context: Context) : super(context.getString(R.string.error_no_internet))

}