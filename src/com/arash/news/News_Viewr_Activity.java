package com.arash.news;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arash.sportkntu.Http_Connection;
import com.arash.sportkntu.Request_package;
import com.arash.sportkntu.URLImageParser;
import com.example.arashtab.R;
import com.example.arashtab.R.id;
import com.example.arashtab.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class News_Viewr_Activity extends Activity {

	 TextView tv;
	String uri="http://sport.kntu.ac.ir/ShowPage.aspx?page_=form&order=show&lang=1&sub=20&PageId=2251&codeV=3&tempname=Template44";
	//String uri="http://sport.kntu.ac.ir/ShowPage.aspx?page_=news&lang=1&tempname=template44&sub=20&isPopUp=false&PageID=4415&PageIDF=32&isPopUp=false";
	String mode="0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news__viewr_);
		uri= getIntent().getStringExtra("url");
		mode=getIntent().getStringExtra("mode");
		tv=(TextView) findViewById(R.id.tv);
		//Toast.makeText(News_Viewr_Activity.this,uri,
			//Toast.LENGTH_LONG).show();
//		Request_package req=new Request_package();
//		req.setMethod("GET");
//		req.setUri(uri);
//		Http_Connection http=new Http_Connection((Activity)News_Viewr_Activity.this){
//			@Override
//			public void onresponse(String res) {
//				String tmp="";
//				Toast.makeText(News_Viewr_Activity.this, res, 8000).show();
//				if(mode.equals("0")){
//		         tmp=res.substring(res.indexOf("<div id=\"ctl00_cphMain_mainContentLoader_mainPartDiv\" class=\"mainPartDivClass\">")
//						, res.indexOf("</table id=mainbody></td>        </tr>        <tr>                  </tr>      </table></td>  </tr></table></div>")); 
//				}
//				else{
//			     tmp=res.substring(res.indexOf("<table id=mainbody")
//							, res.length());
//				}
//				
//				//new URLImageParser(tv, News_Viewr_Activity.this)
//			   tv.setText(Html.fromHtml(tmp,null, null));
//				
//			}
//		};
//	    
//		http.getData(req);
		
		
		RequestQueue queue = Volley.newRequestQueue(News_Viewr_Activity.this);
		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,
		            new Response.Listener<String>() {
			
		
		    @Override
		    public void onResponse(String response) {
		    
				String tmp;
				//Toast.makeText(News_Viewr_Activity.this, response, 8000).show();
				//tv.setText(response.indexOf("<table id=mainbody")+"");
				//tv.setText(response.subSequence(response.indexOf("<table id=mainbody"), response.indexOf("</table id=mainbody>")+20));
				//Log.d("response", response.indexOf("ctl00_cphMain_mainContentLoader_mainPartDiv")+"");
				if(mode.equals("0")){
		         tmp=response.substring(response.indexOf("<div id=\"ctl00_cphMain_mainContentLoader_mainPartDiv\" class=\"mainPartDivClass\">")
						, response.indexOf("</table id=mainbody></td>        </tr>        <tr>                  </tr>      </table></td>  </tr></table></div>")); 
				}	
				else{
					tmp=(String) response.subSequence(response.indexOf("<table id=mainbody"), response.indexOf("</table id=mainbody>")+20);
				}
//				//Toast.makeText(News_Viewr_Activity.this, response.indexOf("ctl00_cphMain_mainContentLoader_mainPartDiv"), 8000).show();
			   tv.setText(Html.fromHtml(tmp,new URLImageParser(tv, News_Viewr_Activity.this), null));

		    }
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(News_Viewr_Activity.this, "error", 8000).show();
				
			}
		});
		
		queue.add(stringRequest);
		
		
		
		
		
	}

}
