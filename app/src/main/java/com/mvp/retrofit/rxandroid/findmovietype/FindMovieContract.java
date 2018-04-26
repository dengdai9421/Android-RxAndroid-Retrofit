package com.mvp.retrofit.rxandroid.findmovietype;

import com.mvp.retrofit.rxandroid.base.BasePersenter;
import com.mvp.retrofit.rxandroid.base.BaseView;
import com.mvp.retrofit.rxandroid.bean.AdsBean;
import com.mvp.retrofit.rxandroid.retrofit.ExceptionHandle;



public interface FindMovieContract {
	interface FindMovieView extends BaseView {
		void requestSuccessful(AdsBean bean);

		void requestFailed(ExceptionHandle.ResponeThrowable e);
		//需要在界面上显示什么就增加什么

	}

	interface FindMoviePersenter extends BasePersenter {
		void getMovie();

	}
}
