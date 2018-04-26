package com.mvp.retrofit.rxandroid.retrofit;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by v339 on 2016/1/18.
 */

public class OkHttpManager {
	private static OkHttpClient okHttpClient;

	public static OkHttpClient getInstance() {
		if (okHttpClient == null) {
			synchronized (OkHttpManager.class) {
				if (okHttpClient == null) {
					OkHttpClient.Builder builder = new OkHttpClient.Builder();
					HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
					interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
					builder.addInterceptor(interceptor);
					//	添加全局参数
					  GlobalParameterInterceptor globalParameterInterceptor = new GlobalParameterInterceptor();
					  builder.addInterceptor(globalParameterInterceptor);
					//超时时间
					builder.connectTimeout(15, TimeUnit.SECONDS);//15S连接超时
					builder.readTimeout(20, TimeUnit.SECONDS);//20s读取超时
					builder.writeTimeout(20, TimeUnit.SECONDS);//20s写入超时
					//错误重连
					builder.retryOnConnectionFailure(true);
					okHttpClient = builder.build();
				}
			}
		}
		return okHttpClient;
	}
}
