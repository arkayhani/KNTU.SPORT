package com.arash.sportkntu;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by saeid on 6/22/2015.
 */
public class Http_Connection {

   public Activity ac;
    public Http_Connection(Activity ac){
        this.ac=ac;
    }
    public  void getData(final Request_package p) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                /////////////////////////////////////
                BufferedReader reader = null;
                String uri = p.getUri();
                if (p.getMethod().equals("GET")) {
                    uri += "?" + p.getEncodedParams();
                }

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(p.getMethod());
                    con.setConnectTimeout(5000);
                    con.setReadTimeout(5000);
                    if (p.getMethod().equals("POST")) {
                        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        con.setRequestProperty("Content-Length","110");
                        con.setRequestProperty("Content-Language", "en-US");
                        con.setUseCaches(true);
                        con.setAllowUserInteraction(true);

                        con.setDoInput(true);

                        con.setDoOutput(true);
                        con.setFixedLengthStreamingMode(p.getEncodedParams().length());
                        con.setFollowRedirects(true);
                        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                        writer.write(p.getEncodedParams());
                        writer.flush();
                    }

                    final StringBuilder sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    con.disconnect();
                    // return sb.toString();
                    ac.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onresponse(sb.toString());
                        }
                    });

                    return ;

                } catch (Exception e) {

                    e.printStackTrace();
                    return ;
                } finally {

                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return ;
                        }
                    }
                }
                //////////////////////////////////////////////
            }
        }).start();



    }




    public void getimage(final Request_package request_package){

        new Thread(new Runnable() {
            @Override
            public void run() {

                final Bitmap b;
                String uri = request_package.getUri();
                if (request_package.getMethod().equals("GET")) {
                    uri += "?" + request_package.getEncodedParams();
                }

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod(request_package.getMethod());
                    con.setConnectTimeout(6000);
                    con.setReadTimeout(6000);
                    if (request_package.getMethod().equals("POST")) {
                        //con.setRequestProperty ("Authorization", basicAuth);
                        //con.setRequestProperty ("Authorization", encodedCredentials);
                        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        con.setRequestProperty("Content-Length","110");
                        con.setRequestProperty("Content-Language", "en-US");
                        con.setUseCaches(true);
                        con.setAllowUserInteraction(true);

                        con.setDoInput(true);

                        con.setDoOutput(true);
                        con.setFixedLengthStreamingMode(request_package.getEncodedParams().length());
                        con.setFollowRedirects(true);
                        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                        writer.write(request_package.getEncodedParams());
                        writer.flush();


                    }
                    InputStream in = con.getInputStream();
                    b = BitmapFactory.decodeStream(in);

                    con.disconnect();

                    ac.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           onimageresponse(b);
                        }
                    });


                } catch (Exception e) {

                    e.printStackTrace();
                }


            }
        }).start();
    }
    
    
    public void onimageresponse(Bitmap b){

    }
    
    
    
    
    


    public int sendreq(final String imagepath, final String imagename, final Request_package p) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                File sourceFile = new File(imagepath);

                if (!sourceFile.isFile()) {

                }
                else
                {
                    try {


                        // open a URL connection to the Servlet
                        FileInputStream fileInputStream = new FileInputStream(sourceFile);
                        URL url=new URL(p.getUri());
                        // Open a HTTP  connection to  the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);



                        dos = new DataOutputStream(conn.getOutputStream());

                        String u="\"image\"";
                        String f="\""+imagename+"\"";
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name="+u+";filename="+f+ lineEnd);
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes(lineEnd);


                        // create a buffer of  maximum size
                        bytesAvailable = fileInputStream.available();

                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math.min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        }

                        // send multipart form data necesssary after file data...
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        // Responses from the server (code and message)

                        final StringBuffer sb;
                        InputStream is = null;

                            is = conn.getInputStream();
                            int ch;
                            sb = new StringBuffer();
                            while ((ch = is.read()) != -1) {
                                sb.append((char) ch);
                            }


                        ac.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onresponse(sb.toString());
                            }
                        });

                        //close the streams //
                        fileInputStream.close();
                        dos.flush();
                        dos.close();


                    } catch (MalformedURLException ex) {


                        ex.printStackTrace();

                    } catch (Exception e) {


                        e.printStackTrace();

                    }



                } // End else block

            }
        }).start();

        return 1;
    }








    public void onresponse(final String res){

    }

}
