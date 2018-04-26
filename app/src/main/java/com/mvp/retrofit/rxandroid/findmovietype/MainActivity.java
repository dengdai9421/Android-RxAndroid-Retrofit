package com.mvp.retrofit.rxandroid.findmovietype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mvp.retrofit.rxandroid.R;
import com.mvp.retrofit.rxandroid.bean.AdsBean;
import com.mvp.retrofit.rxandroid.retrofit.ExceptionHandle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FindMovieContract.FindMovieView {

	@BindView(R.id.list)
	RecyclerView list;
	private RxPersenter persenter;
	private MyAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		initData();
	}

	private void initData() {
		LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		list.setLayoutManager(mLayoutManager);
		adapter = new MyAdapter(this);
		list.setAdapter(adapter);

		persenter = new RxPersenter(this, this);
		persenter.getMovie();
	}


	@Override
	public void requestSuccessful(AdsBean bean) {
		adapter.setData(bean.getData().get(0).getTagList());
	}

	@Override
	public void requestFailed(ExceptionHandle.ResponeThrowable e) {
		Log.e("异常", e.message);
	}

	@Override
	public void showProgress() {

		//	process.show();
	}

	@Override
	public void hideProgress() {
		//	process.hide();
	}

	@Override
	protected void onStop() {
		super.onStop();
		persenter.onViewStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		persenter.onViewDestory();
	}
}
