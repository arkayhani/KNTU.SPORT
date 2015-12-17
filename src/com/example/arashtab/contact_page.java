package com.example.arashtab;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class contact_page extends Fragment{
	private MyAdapterabout myadapterabout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.about_page, container, false);
		//drawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		//
	
		ListView listview=(ListView)v.findViewById(R.id.listsabout);
		myadapterabout=new MyAdapterabout(inflater);
		listview.setAdapter(myadapterabout);
		
		// TODO Auto-generated method stub
		return v;
	}
}
class MyAdapterabout extends BaseAdapter{
	//private Context context;
	private LayoutInflater inflater;
	public int[] dan={R.string.olodan,R.string.bardan,R.string.sandan,R.string.omrdan,R.string.havdan};
	public int[] adr={R.string.oloadr,R.string.baradr,R.string.sanadr,R.string.omradr,R.string.havadr};
	public int[] phon={R.string.oloshm,R.string.barshm,R.string.sanshm,R.string.shmomr,R.string.shmhav};
public  MyAdapterabout(LayoutInflater inflater) {
	//menus=context.getResources().getStringArray(R.array.menu);
	this.inflater=inflater;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dan.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dan[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row=null;
		if(convertView==null){
		//LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		row=inflater.inflate(R.layout.aboutlistrow, parent,false);
		}
		else {
			row=convertView;
		}
		TextView dantextview=(TextView) row.findViewById(R.id.textView2);
		TextView adrtextview=(TextView) row.findViewById(R.id.textView4);
		TextView phontextview=(TextView) row.findViewById(R.id.textView6);
		//ImageView titelimageview=(ImageView)row.findViewById(R.id.imageView1);
		//titelimageview.setImageResource(images[position]);
		dantextview.setText(dan[position]);
		phontextview.setText(phon[position]);
		adrtextview.setText(adr[position]);
		return row;
	}
	
}