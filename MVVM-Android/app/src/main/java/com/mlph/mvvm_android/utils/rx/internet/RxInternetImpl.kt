package com.mlph.mvvm_android.utils.rx.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.mlph.mvvm_android.utils.rx.internet.NoInternetConnectionException
import com.mlph.mvvm_android.utils.rx.internet.RxInternet
import io.reactivex.Single
import javax.inject.Inject

class RxInternetImpl @Inject constructor(
        private var context: Context,
        private var connectivityManager: ConnectivityManager
    ) : RxInternet {

    override fun hasInternetConnection(): Single<Boolean> {
        return Single.defer {
            val networkInfo = connectivityManager.activeNetworkInfo
            val hasConnection = null != networkInfo &&
                    (networkInfo.state == NetworkInfo.State.CONNECTED ||
                            networkInfo.state == NetworkInfo.State.CONNECTING)

            if (hasConnection) {
                Single.just(hasConnection)
            } else {
                Single.error(NoInternetConnectionException())
            }
        }
    }
}