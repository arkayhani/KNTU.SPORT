package com.arash.news;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.arash.sportkntu.Http_Connection;
import com.arash.sportkntu.Request_package;
import com.example.arashtab.R;
import com.example.arashtab.R.id;
import com.example.arashtab.R.layout;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class news_page extends Fragment{
	//private MyAdapternew myadapternew;
	private ProgressDialog pDialog;
	List<news_model> item=new ArrayList<news_model>();
	final int count=15;
	final String []title = new String[count];
	final String []link = new String[count];
	final String []imag = new String[count];
	myadapter_news adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.newslist, container, false);
		//drawerlayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		//
	
		final ListView listview=(ListView)v.findViewById(R.id.list_news);
		//myadapternew=new MyAdapternew(inflater);
		//listview.setAdapter(myadapternew);
		
		
		//too in ghemate ye request doros mikonam be server mifrestam javabesho be soorate
		//text too onresponse migiram
	    //gereftane javab be soorate gheire hamzaman
		//yani ba ferestande baste be server momkene 10 min badtar javab beeyad
		// yani tabe ha tartibi ejra nemishan
		// nokteye mohem ine k farakhani tabeye getdata ro yader nare
		Request_package req=new Request_package();
		req.setMethod("GET");
		req.setUri("http://sport.kntu.ac.ir");
		Activity activity = getActivity();
		Http_Connection http=new Http_Connection(activity){
			
			public void onresponse(String res) {
				pDialog.hide();


				//String []title = new String[count];
				// String []link = new String[count];
				// String []imag = new String[count];
				 Document doc = Jsoup.parse(res);
				 Elements titles = doc.select(".tool_news_sample_template44_block3237b1 a span");
				 Elements links= doc.select(".tool_news_sample_template44_block3237b1 a");
				 Elements imags= doc.select(".tool_news_sample_template44_block3237b2 img");
				 int i = 0;
				 for (Element tt : titles) {
				 title[i++] = tt.html();
				 if(i==count)
				 break;
				 }

				 i = 0;
				 for (Element ll : links) {
				 link[i++] = ll.attr("href");
				 if(i==count)
				 break;

				 }
				 i = 0;
				 for (Element lt : imags) {
				 imag[i++] = lt.attr("src");
				 if(i==count)
				 break;

				 }
				 
				
		         for (int ii = 0; ii < title.length; ii++) {
		 			news_model tmp=new news_model();
		 			tmp.title=title[ii];
		 			tmp.link=link[ii];
		 			tmp.imageadd=imag[ii];
		 			item.add(tmp);		 			
		 		}
		 			adapter=new myadapter_news(getActivity(),R.layout.newslist_row,item);
		 			listview.setAdapter(adapter);
				
			};
		};
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				

				Intent intent = new Intent(getActivity(),News_Viewr_Activity.class);
				intent.putExtra("url", link[position]);
				intent.putExtra("mode", "0");
				startActivity(intent);
				//startActivity();
				
			}
		});

		
		http.getData(req);
		pDialog = new ProgressDialog(getActivity());
	      pDialog.setMessage("در حال دریافت...لطفا منتظر بمانید");
	      pDialog.setIndeterminate(false);
	      pDialog.setCancelable(false);
	      pDialog.show();
	
		
		
		
		
		// TODO Auto-generated method stub
		return v;
	}
}
