package com.mlph.mvvm_android.api

class ApiErrorDataDto {

    private var code: String? = null

    private var success: Boolean? = null

    private lateinit var message: String

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun isSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

    fun getMessage(): String {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

}