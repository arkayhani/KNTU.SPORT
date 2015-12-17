package com.arash.calender;

import com.arash.news.News_Viewr_Activity;
import com.example.arashtab.R;
import com.example.arashtab.R.array;
import com.example.arashtab.R.id;
import com.example.arashtab.R.layout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class calender_foghbarname extends Fragment{
	private MyAdaptercalender_foghbarname myadaptercalender;
	private ListView listview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.calender_page, container, false);
		//drawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		//
		v.setFocusableInTouchMode(true);
		v.requestFocus();
		v.setOnKeyListener(new View.OnKeyListener() {
	        @Override
	        public boolean onKey(View v, int keyCode, KeyEvent event) {
	    //        Log.i(tag, "keyCode: " + keyCode);
	            if( keyCode == KeyEvent.KEYCODE_BACK ) {
	      //              Log.i(tag, "onKey Back listener is working!!!");
	                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
	                return true;
	            } else {
	                return false;
	            }
	        }

			
	    });
		String[] s=getActivity().getResources().getStringArray(R.array.calenders);
		TextView txt= (TextView)(v.findViewById(R.id.textcalender1));
		txt.setText(s[0]);
		listview=(ListView)v.findViewById(R.id.list_calender1);
		myadaptercalender =new MyAdaptercalender_foghbarname(getActivity());
		listview.setAdapter(myadaptercalender);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Intent intent = new Intent(getActivity(),News_Viewr_Activity.class);

				selectitem(position);
				switch (position) {
				case 0:
					intent.putExtra("mode", "1");
					intent.putExtra("url", "http://sport.kntu.ac.ir/ShowPage.aspx?page_=form&order=show&lang=1&sub=20&PageId=2245&codeV=8&tempname=Template44");
					startActivity(intent);
					break;
				case 2:
					//Intent intent = new Intent(getActivity(),News_Viewr_Activity.class);
					intent.putExtra("mode", "1");
					intent.putExtra("url", "http://sport.kntu.ac.ir/ShowPage.aspx?page_=form&order=show&lang=1&sub=20&PageId=2246&codeV=5&tempname=Template44");
					startActivity(intent);
					break;
				case 3:
					//Intent intent = new Intent(getActivity(),News_Viewr_Activity.class);
					intent.putExtra("mode", "1");
					intent.putExtra("url", "http://sport.kntu.ac.ir/ShowPage.aspx?page_=form&order=show&lang=1&sub=20&PageId=2247&codeV=2&tempname=Template44");
					startActivity(intent);
					break;
				case 1:
					//Intent intent = new Intent(getActivity(),News_Viewr_Activity.class);
					intent.putExtra("mode", "1");
					intent.putExtra("url", "http://sport.kntu.ac.ir/ShowPage.aspx?page_=form&order=show&lang=1&sub=20&PageId=2248&codeV=4&tempname=Template44");
					startActivity(intent);
					break;
				case 4:
					//Intent intent = new Intent(getActivity(),News_Viewr_Activity.class);
					intent.putExtra("mode", "1");
					intent.putExtra("url", "http://sport.kntu.ac.ir/ShowPage.aspx?page_=form&order=show&lang=1&sub=20&PageId=2249&codeV=1&tempname=Template44");
					startActivity(intent);
					break;


				default:
					break;
				}
				
			}
			
		});
		
		// TODO Auto-generated method stub
		return v;
	}
	private void selectitem(int position) {
		// TODO Auto-generated method stub
		listview.setItemChecked(position, true);
	}
}
class MyAdaptercalender_foghbarname extends BaseAdapter{
	//private Context context;
	//private LayoutInflater inflater;

	public String[] s; 
	public Context contex;
public  MyAdaptercalender_foghbarname(Context contex) {
	this.contex=contex;
	s=contex.getResources().getStringArray(R.array.daneshkadehafogh);
	
//	this.inflater=inflater;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return s.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return s[arg0];
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
		LayoutInflater inflater=(LayoutInflater) contex.getSystemService(contex.LAYOUT_INFLATER_SERVICE);
		row=inflater.inflate(R.layout.calender_row, parent,false);
		}
		else {
			row=convertView;
		}
		TextView textview=(TextView) row.findViewById(R.id.textView1);
		
		//ImageView titelimageview=(ImageView)row.findViewById(R.id.imageView1);
		//titelimageview.setImageResource(images[position]);
		textview.setText(s[position]);
		
		return row;
	}
	
}