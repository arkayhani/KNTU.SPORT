package com.example.arashtab;

import java.util.zip.Inflater;











import com.arash.calender.calender_page;
import com.arash.news.news_page;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Telephony.Sms.Conversations;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity   {
private DrawerLayout drawerlayout;
private ListView listview;
private String[] planets;
private ActionBarDrawerToggle drawerlistener;
private MyAdapter myadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.kntu1)
		        .setContentTitle("Sport KNTU")
		        .setContentText("new News is available");
		//inflater.getClass()
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, MainActivity.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);
		int notifyID = 1;
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		//mNotificationManager.notify(notifyID, mBuilder.build());
		setTitle("Menu");
		setContentView(R.layout.activity_main);
		drawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		//planets=getResources().getStringArray(R.array.planets);
		listview=(ListView)findViewById(R.id.drawerList);
		myadapter=new MyAdapter(this);
		listview.setAdapter(myadapter);
		///
		//FragmentManager fragmentManager = getFragmentManager();
		  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
		 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
		   
	    //    fragmentManager.beginTransaction().replace(R.id.mainContent, new fragment_home()).commit();
		//listview.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,planets));
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(this, planets[position]+"was selected", Toast.LENGTH_LONG).show();
				
				selectitem(position);
				if(position==0)
				{
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					   
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new fragment_home()).commit();
				}
				if(position==1)
				{
					
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					  
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new news_page()).commit();
				}
				if(position==2)
				{
					
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					  
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new calender_page()).commit();
				}
				if(position==3)
				{
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					  
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new staff_page()).commit();
				}
				if(position==4)
				{
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					  
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new contact_page()).commit();
				}
				if(position==5)
				{
					   FragmentManager fragmentManager = getFragmentManager();
					  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
					 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
					  
				        fragmentManager.beginTransaction().replace(R.id.mainContent, new prog_page()).commit();
				}
					
			}
		});
		drawerlistener=new ActionBarDrawerToggle(this, drawerlayout, R.drawable.ic_drawer,
				R.string.drawer_open,R.string.drawer_close){
			@Override
					public void onDrawerOpened(View drawerView) {
						// TODO Auto-generated method stub
						super.onDrawerOpened(drawerView);
					}
			@Override
					public void onDrawerClosed(View drawerView) {
						// TODO Auto-generated method stub
						super.onDrawerClosed(drawerView);
					}
		};
		drawerlayout.setDrawerListener(drawerlistener);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		 FragmentManager fragmentManager = getFragmentManager();
		  // android.app.Fragment fragment=fragmentManager.findFragmentById(com.example.arashtab.R.id.mainContent);
		 //  android.app.FragmentTransaction ft =fragmentManager.beginTransaction();
		   
	        fragmentManager.beginTransaction().replace(R.id.mainContent, new fragment_home()).commit();
		
		
		
	}
	
	private void selectitem(int position) {
		// TODO Auto-generated method stub
		listview.setItemChecked(position, true);
	}
	
	private void settitle(String string) {
		// TODO Auto-generated method stub
		getActionBar().setTitle(string);
	}
	@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onPostCreate(savedInstanceState);
			drawerlistener.syncState();
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		drawerlistener.onConfigurationChanged(newConfig);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
	//	if (id == R.id.action_settings) {
		//	return true;
		//}
		if(drawerlistener.onOptionsItemSelected(item))
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
class MyAdapter extends BaseAdapter{
	private Context context;
	public String[] menus;
	int[] images={R.drawable.h2,R.drawable.n2,R.drawable.calendar,R.drawable.stafff,R.drawable.cont,R.drawable.about};
public  MyAdapter(Context context) {
	menus=context.getResources().getStringArray(R.array.menu);
	this.context=context;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menus.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return menus[arg0];
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
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		row=inflater.inflate(R.layout.custom_row, parent,false);
		}
		else {
			row=convertView;
		}
		TextView titeltextview=(TextView) row.findViewById(R.id.textView1);
		ImageView titelimageview=(ImageView)row.findViewById(R.id.imageView1);
		titelimageview.setImageResource(images[position]);
		titeltextview.setText(menus[position]);
		return row;
	}
	
}
