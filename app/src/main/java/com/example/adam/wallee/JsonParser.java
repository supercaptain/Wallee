package com.example.adam.wallee;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.adam.wallee.Model.UserModel;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adam on 30.1.2015.
 */
public class JsonParser {

    private static final String TAG_ID = "id";
    public static final String TAG_TASK = "task";
    public static final String TAG_STATUS = "status";
    public  static final String TAG_DATE = "created_at:";
    Context context;

    public  JsonParser(Context context){
        this.context = context;
    }


    public void parseJson(String json){

        if(json != null){
        try {
            JSONObject object = new JSONObject(json);

            //Check if object has Array Task
            JSONArray tasks = object.getJSONArray("tasks");

            for(int i = 0; i < tasks.length(); i++){
                JSONObject item = tasks.getJSONObject(i);

                String id = item.getString(TAG_ID);
                String task = item.getString(TAG_TASK);
                String status = item.getString(TAG_STATUS);
                String date = item.getString(TAG_DATE);

                HashMap<String, String> itemlist = new HashMap<String, String>();
                itemlist.put(TAG_ID,id);
                itemlist.put(TAG_TASK,task);
                itemlist.put(TAG_STATUS,status);
                itemlist.put(TAG_DATE,date);


            }

        }catch (JSONException e) {
            e.printStackTrace();
        }




        }



    }


    public String getContentFromUrl(String adress){

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String parserString = "";

        //check connect
        if(isOnline()){
            try {
                URL url = new URL(adress);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String helper= "";
                while ((helper = bufferedReader.readLine()) != null){
                    parserString += helper;
                }
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        return parserString;

    }


    public String getDataFromServer(String json) throws IOException{

        String parserString = "";
        if(isOnline()){
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://10.0.2.2:3000");

            get.setHeader("Accept", "application/json");
            get.setHeader("Content-Type", "application/json");
            //get.setEntity(new StringEntity(json));

            //HttpResponse response = client.execute(post);

            HttpResponse response = client.execute(get);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));


            String helper= "";
            while ((helper = rd.readLine()) != null){
                parserString += helper;
            }
        }
        return parserString;
    }



    public void sendDataToServer(JSONObject json) throws IOException, ClientProtocolException {

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("task", "vyser si oko"));
        nameValuePair.add(new BasicNameValuePair("status", "0"));

        if(isOnline()){
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://10.0.2.2:3000");

            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");

            StringEntity entity = new StringEntity(json.toString());
            entity.setContentType("application/json;charset=UTF-8");
            //entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));

            post.setEntity(entity);

            HttpResponse response = client.execute(post);

        }
    }

    public HttpResponse sendDataToServerLogin(JSONObject json) throws IOException, ClientProtocolException {

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        HttpResponse response = null;
        if(isOnline()){
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://10.0.2.2:3000/login");

            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");

            StringEntity entity = new StringEntity(json.toString());
            entity.setContentType("application/json;charset=UTF-8");
            //entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));

            post.setEntity(entity);

            response = client.execute(post);

        }
        return  response;
    }

    public String urlBuider(UserModel userModel){

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("10.0.2.2:3000").appendQueryParameter("token", userModel.getApiKey());
        return builder.build().toString();

    }


    public boolean isOnline(){

        ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();

        return (netInfo != null && netInfo.isConnected());
    }

}
