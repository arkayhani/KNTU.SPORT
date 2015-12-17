package com.arash.sportkntu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class URLImageParser implements ImageGetter {
    Context c;
    View container;
    
    /***
     * Construct the URLImageParser which will execute AsyncTask and refresh the container
     * @param t
     * @param c
     */
    public URLImageParser(View t, Context c) {
        
        this.c = c;
        this.container = t;
    }

   @Override
    public Drawable getDrawable(String source) {
        URLDrawable urlDrawable = new URLDrawable();
        
        if(source.indexOf("http://www.kntu.ac.ir")<0){
        	source="http://www.kntu.ac.ir"+source;
        	
        }
        
        // get the actual source
        ImageGetterAsyncTask asyncTask = 
            new ImageGetterAsyncTask( urlDrawable);

        asyncTask.execute(source);

        // return reference to URLDrawable where I will change with actual image from
        // the src tag
        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable>  {
        URLDrawable urlDrawable;

        
        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        @Override
        protected void onPostExecute(Drawable result) {
        	Activity ac=(Activity) c;
            DisplayMetrics displaymetrics = new DisplayMetrics();
        	ac.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        	try{
        		
               
                	urlDrawable.setBounds(0,
                    		0,
                            displaymetrics.widthPixels,
                    		0+result.getIntrinsicHeight()*displaymetrics.widthPixels/result.getIntrinsicWidth()); 
                

            // set the correct bound according to the result from HTTP call

            
//            urlDrawable.setBounds(0, 0, 0 + displaymetrics.widthPixels, 0 
//                    + displaymetrics.widthPixels); 
        	}catch(Exception e){};
            // change the reference of the current drawable to the result
            // from the HTTP call
            urlDrawable.drawable = result;
                URLImageParser.this.container.setMinimumHeight(URLImageParser.this.container.getHeight() + result.getIntrinsicHeight() * displaymetrics.widthPixels / result.getIntrinsicWidth());
                ((TextView) URLImageParser.this.container).setHeight(URLImageParser.this.container.getHeight() + result.getIntrinsicHeight() * displaymetrics.widthPixels / result.getIntrinsicWidth());


            // redraw the image by invalidating the container
            URLImageParser.this.container.invalidate();
        }

        /***
         * Get the Drawable from URL
         * @param urlString
         * @return
         */
        public Drawable fetchDrawable(String urlString) {
            try {
            	Activity ac=(Activity) c;
                DisplayMetrics displaymetrics = new DisplayMetrics();
            	ac.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            	
                InputStream is = fetch(urlString);
                Drawable drawable = Drawable.createFromStream(is, "src");
                    drawable.setBounds(0,
                    		0, 
                    		displaymetrics.widthPixels, 
                    		0+drawable.getIntrinsicHeight()*displaymetrics.widthPixels/drawable.getIntrinsicWidth()); 

                return drawable;
            } catch (Exception e) {
                return null;
            } 
        }

        private InputStream fetch(String urlString) throws MalformedURLException, IOException {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(urlString);
            HttpResponse response = httpClient.execute(request);
            return response.getEntity().getContent();
        }
    }
    
    @SuppressWarnings("deprecation")
	public class URLDrawable extends BitmapDrawable {
        // the drawable that you need to set, you could set the initial drawing
        // with the loading image if you need to
        protected Drawable drawable;

        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function later
            if(drawable != null) {
                drawable.draw(canvas);
            }
        }
    }
}
