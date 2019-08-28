package com.mlph.mvvm_android.api

class ApiErrorDto {

    private var code: Int = 0

    private lateinit var data: ApiErrorDataDto

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }

    fun getData(): ApiErrorDataDto {
        return data
    }

    fun setData(data: ApiErrorDataDto) {
        this.data = data
    }

}