package com.example.zalegoadmin.dbstorage;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zalegoadmin on 3/22/2017.
 */
public class SendData extends AsyncTask<Void,Void,String> {
    String username;
    String url;
    String response=null;


    public SendData(String username,String url){
        this.username=username;
        this.url=url;

    }

    @Override
    protected String doInBackground(Void... params) {

        HashMap<String,String> data= new HashMap<String,String>();
        data.put("username_id",username);
        URL mainURL;
        try{
            mainURL=new URL(url);
            Log.d("cecil", url);
            HttpURLConnection conn=(HttpURLConnection) mainURL.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os=conn.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getPostDataString(data));
            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK){
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response=br.readLine();
                Log.d("cecil", response);
            }else {
                response="error-posting";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
    private String getPostDataString(HashMap<String,String>params)throws UnsupportedEncodingException {
        StringBuilder result=new StringBuilder();
        boolean first=true;
        for (Map.Entry<String,String>entry:params.entrySet()){
            if(first)
                first=false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }

        return  result.toString();


    }
}
