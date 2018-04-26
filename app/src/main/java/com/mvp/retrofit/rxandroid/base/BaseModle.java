package com.mvp.retrofit.rxandroid.base;

import com.mvp.retrofit.rxandroid.bean.AdsBean;
import com.mvp.retrofit.rxandroid.retrofit.RetrofitClient;

import io.reactivex.Observable;



public class BaseModle {
	/**
	 * 获取电影分类
	 *
	 * @return
	 */

	public Observable<AdsBean> getMovieTypeList() {
		return RetrofitClient
				.getInstance()
				.api()
				.getMovieTypeList();
	}
}
