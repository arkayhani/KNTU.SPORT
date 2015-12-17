package com.arash.calender;

import com.example.arashtab.R;
import com.example.arashtab.R.array;
import com.example.arashtab.R.id;
import com.example.arashtab.R.layout;
import com.example.arashtab.R.string;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class calender_page extends Fragment{
	private MyAdaptercalender myadaptercalender;
	private ListView listview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.calender_page, container, false);
		//drawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		//
		String s=getActivity().getResources().getString(R.string.calender);
		TextView txt= (TextView)(v.findViewById(R.id.textcalender1));
		txt.setText(s);
		listview=(ListView)v.findViewById(R.id.list_calender1);
		myadaptercalender =new MyAdaptercalender(getActivity());
		listview.setAdapter(myadaptercalender);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				
				selectitem(position);
				if(position==0)
				{
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					   
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new calender_foghbarname()).addToBackStack("tag").commit();
				}
				if(position==1)
				{
					
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					  
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new calender_heiatelmi()).addToBackStack("tag").commit();
				}
				if(position==2)
				{
					
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					  
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new calender_carkonan()).addToBackStack("tag").commit();
				}
				if(position==3)
				{
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					  
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new calender_khabgah()).addToBackStack("tag").commit();
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
class MyAdaptercalender extends BaseAdapter{
	//private Context context;
	//private LayoutInflater inflater;

	public String[] s; 
	public Context contex;
public  MyAdaptercalender(Context contex) {
	this.contex=contex;
	s=contex.getResources().getStringArray(R.array.calenders);
	
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