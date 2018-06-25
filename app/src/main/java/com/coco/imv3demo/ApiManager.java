package com.coco.imv3demo;

import android.text.TextUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ydx on 16-12-28.
 */
public class ApiManager {

    private static ApiManager apiManager = new ApiManager();
    private static String baseUrl;
    private int count;
    public String getBaseUrl(){
        return baseUrl;
    }
    private ApiManager(){}
    public static ApiManager getInstance(){
        return apiManager;
    }
    /*构建证书 */
//    SSLSocketFactory sslSocketFactory = HttpsFactory.getSSLSocketFactory();

//    CertificatePinner certificatePinner = new CertificatePinner.Builder()
//            .add("api.heydaycn.com","sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=").build();

    /* 拦截器 */
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            try {
                Request request = chain.request();
                String token = request.header("x-auth-token");
                Request r;
                if (!TextUtils.isEmpty(token)) {
                    r = request.newBuilder().build();
                } else {
                    r = request.newBuilder().removeHeader("x-auth-token").build();
                }

                return chain.proceed(r);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    };

    private HttpLoggingInterceptor getLogInterceptor(){
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(String message) {
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }


    /* client */
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)//10s连接超时
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addNetworkInterceptor(getLogInterceptor())//添加网络拦截器
//            .sslSocketFactory(sslSocketFactory)
//            .hostnameVerifier(HttpsFactory.getHostnameVerifier(new String[]{"api.heydaycn.com"}))
//            .certificatePinner(certificatePinner)
            .addInterceptor(interceptor)//添加拦截器
            .build();//构建okhttpclient



    public  ApiService  apiService = getRetrofit(baseUrl);

    public void changedBaseUrl(String url){
        apiService = getRetrofit(url);
    }

    ApiService getRetrofit(String url){
        baseUrl = url;

        baseUrl="https://api.heydaycn.com/";
//        if(count==0) {//??
//            if (VersionManager.getInstance().isTestVersion()) {
//                baseUrl = App.getInstances().getResources().getString(R.string.api_test);//测试网址
//            } else {
//                baseUrl = App.getInstances().getResources().getString(R.string.api_normal);//正常网址
//            }
//        }
//        Logger.d("request url:"+baseUrl);
        count++;
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build().create(ApiService.class);
    }


}
