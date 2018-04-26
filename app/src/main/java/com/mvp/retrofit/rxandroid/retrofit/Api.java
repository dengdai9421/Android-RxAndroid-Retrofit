package com.mvp.retrofit.rxandroid.retrofit;


import com.mvp.retrofit.rxandroid.bean.AdsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface Api {

	@GET("mmdb/search/movie/tag/types.json")
	Observable<AdsBean> getMovieTypeList();
}
