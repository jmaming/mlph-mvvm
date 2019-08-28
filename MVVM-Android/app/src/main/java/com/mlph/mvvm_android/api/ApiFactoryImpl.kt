package com.mlph.mvvm_android.api

import com.mlph.mvvm_android.utils.Constants
import com.mlph.mvvm_android.utils.prefs.PrefsUtil
import com.mlph.mvvm_android.utils.session.UserSession
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiFactoryImpl @Inject constructor(private var userSession: UserSession,
                                         private var prefsUtil: PrefsUtil
) : ApiFactory {

    override fun <T> create(apiClass: Class<T>): Single<T> {
        return retrofit().map { retrofit -> retrofit.create(apiClass) }
    }

    override fun <T> createWithHeader(apiClass: Class<T>): Single<T> {
        return retrofitWithHeader().map {
            retrofit -> retrofit.create(apiClass)
        }
    }

    override fun retrofit(): Single<Retrofit> {
        return client().flatMap(this::intoRetrofit)
    }

    private fun retrofitWithHeader() : Single<Retrofit> {
        return clientWithHeader().flatMap(this::intoRetrofit)
    }

    private fun client(): Single<OkHttpClient> {
        return Single
            .fromCallable {
                OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
            }
            .flatMap(this::addLoggingInterceptor)
            .map(OkHttpClient.Builder::build)
    }

    private fun clientWithHeader(): Single<OkHttpClient> {
        return Single
            .fromCallable {
                OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
            }
            .flatMap(this::addLoggingInterceptor)
//            .flatMap(this::addInterceptorForAuthorizationHeader)
            .map(OkHttpClient.Builder::build)
    }

    private fun intoRetrofit(client: OkHttpClient): Single<Retrofit> {
        return Single
            .fromCallable {
                Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
        return Single.fromCallable {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(loggingInterceptor)

            builder
        }
    }

    /**
     * Intercept an outgoing API request and inject Bearer {{token}} to it's
     * authorization header.
     * */
    //TODO
//    private fun addInterceptorForAuthorizationHeader(builder: OkHttpClient.Builder) : Single<OkHttpClient.Builder> {
//        return userSession.hasExistingUserSession()
//            .flatMap { hasExisting ->
//                if (hasExisting) {
//                    userSession.getToken()
//                        .map { token ->
//                            builder.addInterceptor { chain ->
//                                val original = chain.request()
//                                val request = original.newBuilder()
//                                    .header("access_token", token)
//                                    .build()
//
//                                chain.proceed(request)
//                            }
//                            builder
//                        }
//                } else {
//                    prefsUtil.convertJsonStringToObject(PrefsKey.SIGN_UP_PARAMS, SignUpParamsDto::class.java)
//                        .map { signUpParamsDto ->
//                            builder.addInterceptor { chain ->
//                                val original = chain.request()
//                                val request = original.newBuilder()
//                                    .header("access_token", signUpParamsDto.getToken()!!)
//                                    .build()
//
//                                chain.proceed(request)
//                            }
//                            builder
//                        }
//                }
//            }
//    }

}