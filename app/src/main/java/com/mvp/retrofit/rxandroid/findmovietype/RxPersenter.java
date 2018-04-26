package com.mvp.retrofit.rxandroid.findmovietype;

import android.app.Activity;

import com.mvp.retrofit.rxandroid.base.BaseModle;
import com.mvp.retrofit.rxandroid.bean.AdsBean;
import com.mvp.retrofit.rxandroid.retrofit.ExceptionHandle;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RxPersenter implements FindMovieContract.FindMoviePersenter {
	private Activity activity;
	private BaseModle modle;
	private FindMovieContract.FindMovieView rxView;
	private Disposable disposable;

	public RxPersenter(Activity activity, FindMovieContract.FindMovieView rxView) {
		this.activity = activity;
		this.rxView = rxView;
		this.modle = new BaseModle();
	}

	@Override
	public void getMovie() {
		rxView.showProgress();
		Observable<AdsBean> observable = modle.getMovieTypeList();
		disposable = observable.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<AdsBean>() {
					@Override
					public void accept(AdsBean adsBean) throws Exception {  //等于Onnext
						rxView.requestSuccessful(adsBean);
					}
				}, new Consumer<Throwable>() {   //等于onError
					@Override
					public void accept(Throwable throwable) throws Exception {
						rxView.requestFailed(ExceptionHandle.handleException(throwable));
					}
				}, new Action() { //oncomplate
					@Override
					public void run() throws Exception {
						rxView.hideProgress();
					}
				});
	}

	@Override
	public void onViewStop() {

	}

	@Override
	public void onViewDestory() {
		if (disposable != null && !disposable.isDisposed()) {
			disposable.dispose();
		}
	}

}
