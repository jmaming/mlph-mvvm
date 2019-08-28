package com.mlph.mvvm_android.utils.rx.errorparser

import com.mlph.mvvm_android.api.ApiErrorDto
import io.reactivex.Single
import retrofit2.Response

interface RxErrorParser {

    fun parseError(response: Response<*>) : Single<ApiErrorDto>

}