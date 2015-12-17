package com.arash.news;

import java.util.List;

import com.arash.sportkntu.Http_Connection;
import com.arash.sportkntu.Request_package;
import com.example.arashtab.R;
import com.example.arashtab.R.id;
import com.example.arashtab.R.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myadapter_news extends ArrayAdapter<news_model> {

	List<news_model> ltemp;
	private Context c;
	public myadapter_news(Context context, int textViewResourceId,
			List<news_model> objects) {
		super(context, textViewResourceId, objects);
		ltemp=objects;
		c=context;
		
	}

	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater i=(LayoutInflater)c.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View v=i.inflate(R.layout.newslist_row, parent,false);
		
		TextView t= (TextView) v.findViewById(R.id.textView1);
		ImageView img=(ImageView) v.findViewById(R.id.imageView1);
		
		t.setText(ltemp.get(position).title);
		if(ltemp.get(position).thumbnail==null){
			
			getimage(position);
		}
		else{
			
			img.setImageBitmap(ltemp.get(position).thumbnail);
		}
		
		
        return v;
	}
	
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}
	
	private void getimage(final int position){
		
		Request_package request_package=new Request_package();
		request_package.setMethod("GET");
		request_package.setUri(ltemp.get(position).imageadd);
		Http_Connection http_Connection=new Http_Connection((Activity) c){
			@Override
			public void onimageresponse(Bitmap b) {
		          ltemp.get(position).thumbnail=b;
		          notifyDataSetChanged();
			}
		};
		
		http_Connection.getimage(request_package);
		
	}
	
}
