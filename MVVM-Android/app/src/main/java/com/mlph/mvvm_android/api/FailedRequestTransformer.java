package com.mlph.mvvm_android.api;

import com.mlph.mvvm_android.utils.rx.errorparser.RxErrorParser;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import retrofit2.Response;

import javax.inject.Inject;

public class FailedRequestTransformer<T> implements SingleTransformer<Response<T>, Response<T>> {

    private RxErrorParser rxErrorParser;

    @Inject
    public FailedRequestTransformer(RxErrorParser rxErrorParser) {
        this.rxErrorParser = rxErrorParser;
    }

    @Override
    public SingleSource<Response<T>> apply(Single<Response<T>> upstream) {
        return upstream.flatMap(response -> {
                    if (null != response.errorBody()) {
                        return rxErrorParser.parseError(response)
                                .flatMap(apiErrorDto -> {
                                    // retrieve the error message thrown by the API.
                                    if (apiErrorDto.getData().getMessage().equals("Your session is expired. Please login again")
                                            || apiErrorDto.getData().getMessage().equals("Invalid Token")) {
                                        return Single.error(new SessionExpiredException());
                                    } else {
                                        return Single.error(new FailedRequestException(apiErrorDto.getData().getMessage()));
                                    }
                                });
                    }
                    return Single.just(response);
                }
        );
    }

}
