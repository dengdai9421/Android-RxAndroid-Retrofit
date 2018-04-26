package com.mvp.retrofit.rxandroid.findmovietype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvp.retrofit.rxandroid.R;
import com.mvp.retrofit.rxandroid.bean.AdsBean;

import java.util.ArrayList;
import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
	private Context context;
	private List<AdsBean.DataBean.TagListBean> data;

	public MyAdapter(Context context) {
		data = new ArrayList<>();
		this.context = context;

	}

	public MyAdapter(Context context, List<AdsBean.DataBean.TagListBean> date) {
		data = new ArrayList<>();
		this.context = context;
		this.data.addAll(date);

	}

	public void setData(List<AdsBean.DataBean.TagListBean> date) {
		this.data.addAll(date);
		notifyDataSetChanged();
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.adapter_item, parent, false);

		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		holder.txt.setText(data.get(position).getTagName());
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		TextView txt;

		public MyViewHolder(View itemView) {
			super(itemView);
			txt = itemView.findViewById(R.id.txt_item);
		}
	}

}
