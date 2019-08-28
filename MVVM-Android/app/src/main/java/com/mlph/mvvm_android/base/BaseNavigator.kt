package com.mlph.mvvm_android.base

interface BaseNavigator {

    fun showProgressDialog()

    fun dismissProgressDialog()

    fun showErrorMessage(errorMessage: String)

    fun onDismiss()

    fun onSessionExpired()

}