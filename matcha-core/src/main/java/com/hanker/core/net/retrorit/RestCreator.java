package com.hanker.core.net.retrorit;

import com.hanker.core.net.app.ConfigKeys;
import com.hanker.core.net.app.Matcha;
import com.hanker.core.utils.MatChaLogger;
import com.hanker.core.utils.UtilForRequest;

import java.io.IOException;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/10/25.
 */

public final class RestCreator {
    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
//        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

//        private static OkHttpClient.Builder addInterceptor() {
//            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
//                for (Interceptor interceptor : INTERCEPTORS) {
//                    BUILDER.addInterceptor(interceptor);
//                }
//            }
//            return BUILDER;
//        }

//        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
        private static final OkHttpClient OK_HTTP_CLIENT = BUILDER
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        String t = UtilForRequest.genSn();
                        Request request = chain.request().newBuilder()
                                .addHeader(ApiConfig.FIELD_APPID,"102")
                                .addHeader(ApiConfig.FIELD_CHANNEL,"360")
                                .addHeader(ApiConfig.FIELD_TERVER,"3.1")
                                .addHeader(ApiConfig.FIELD_DEVICEID,UtilForRequest.getDeviceId())
                                .addHeader("ExpiresTime",t)
                                .addHeader("tcp",UtilForRequest.getHS(t, (String) getParams().get(ApiConfig.FIELD_SIGN)))
                                .build();
                        MatChaLogger.d("header", request.toString());
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = Matcha.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }
}
