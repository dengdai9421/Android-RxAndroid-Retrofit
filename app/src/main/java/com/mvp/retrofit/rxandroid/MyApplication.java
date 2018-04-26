package com.mvp.retrofit.rxandroid;

import android.app.Application;

import com.mvp.retrofit.rxandroid.retrofit.OkHttpManager;
import com.mvp.retrofit.rxandroid.retrofit.RetrofitClient;



public class MyApplication extends Application {
	private static MyApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		RetrofitClient.initClient_BaseUrl(OkHttpManager.getInstance(), Constants.BASE_URL);
	}

	public static MyApplication getInstance() {
		return instance;
	}
}
