package com.mlph.mvvm_android.utils.rx.errorparser;

import android.content.Context;

import com.mlph.mvvm_android.api.ApiErrorDto;
import com.mlph.mvvm_android.api.ApiFactory;
import com.mlph.mvvm_android.api.FailedRequestException;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Response;

import javax.inject.Inject;
import java.lang.annotation.Annotation;

public class RxErrorParserImpl implements RxErrorParser {

    private ApiFactory apiFactory;

    private Context context;

    @Inject
    public RxErrorParserImpl(Context context, ApiFactory apiFactory) {
        this.context = context;
        this.apiFactory = apiFactory;
    }

    @Override
    public Single<ApiErrorDto> parseError(Response response) {
        return apiFactory
                .retrofit()
                .flatMap(retrofit -> {

                    /*
                     * Reading of ResponseBody is a one-shot consumption only, thus we're securing
                     * a copy of it so we could read the ResponseBody any time we want to.
                     * */
                    final ResponseBody responseBody = response.errorBody();
                    final Buffer bufferClone = responseBody.source().buffer().clone();

                    final ResponseBody responseBodyClone = ResponseBody
                            .create(responseBody.contentType(),
                                    responseBody.contentLength(),
                                    bufferClone);

                    return Single.fromCallable(() -> retrofit
                            .responseBodyConverter(ApiErrorDto.class, new Annotation[0]))
                            .flatMap(converter -> original(converter, responseBodyClone))
                            .onErrorResumeNext(fallback(responseBody));
                })
                .onErrorResumeNext(Single.error(new FailedRequestException(context)));
    }

    private Single<ApiErrorDto> original(Converter<ResponseBody, Object> converter,
                                         ResponseBody responseBody) {
        return Single.fromCallable(() -> (ApiErrorDto) converter.convert(responseBody));
    }

    private Single<ApiErrorDto> fallback(ResponseBody responseBody) {
        return apiFactory
                .retrofit()
                .flatMap(retrofit ->
                        Single.fromCallable(() ->
                                retrofit.responseBodyConverter(ApiErrorDto.class,
                                        new Annotation[0])))
                .flatMap(converter -> {

                    ApiErrorDto fallbackDto = (ApiErrorDto) converter
                            .convert(responseBody);

                    ApiErrorDto apiErrorDto = new ApiErrorDto();

                    apiErrorDto.setCode(fallbackDto.getCode());
                    apiErrorDto.setData(fallbackDto.getData());

                    return Single.fromCallable(() -> apiErrorDto);
                });
    }

}
