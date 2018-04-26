package com.mvp.retrofit.rxandroid.base;



public interface BaseView<T> {
	//显示加载中
	void showProgress();

	//加载完成
	void hideProgress();

}
