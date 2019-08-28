package com.mlph.mvvm_android.api

import android.content.Context
import com.mlph.mvvm_android.R

class FailedRequestException : Throwable {

    constructor(context: Context) : super(context.getString(R.string.generic_error_message))

    constructor(errorMessage: String) : super(errorMessage)

}