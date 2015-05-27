package com.example.adam.wallee.Activities;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adam.wallee.JsonParser;
import com.example.adam.wallee.Model.UserModel;
import com.example.adam.wallee.R;

import java.io.IOException;
import java.net.URI;


public class DownloadActivity extends ActionBarActivity {

    TextView textView;
    TextView dw;
    JsonParser jsonParser;

    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Toolbar t = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(t);

        textView = (TextView) findViewById(R.id.textparsing);
        dw = (TextView) findViewById(R.id.download);
        this.jsonParser = new JsonParser(this);
        if(jsonParser.isOnline())
            textView.setText("Jsme pripojeni");
        else
            textView.setText("Nejste pripojeni");

        /*
        user = new UserModel();
        Bundle extras  = getIntent().getExtras();
        user = (UserModel) extras.getSerializable("User");
        textView.setText(user.getApiKey());
        String url = jsonParser.urlBuider(user);
        */
        Button b = (Button) findViewById(R.id.buttonDownload);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jsonParser.isOnline()){
                      //new Downloader().execute("http://10.0.2.2:3000/?token+"+user.getApiKey());
                    new Downloader().execute("http://10.0.2.2:3000");
                }
                else
                    textView.setText("Erorr after click");
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    class Downloader extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... params) {
            //return jsonParser.getContentFromUrl(params[0]);
            String s = null;
                s = jsonParser.getContentFromUrl(params[0]);
/*
                try{
                    s = jsonParser.getDataFromServer(params[0]);
                }
                catch (IOException e){

                }
*/

                //dw.setText(s);


            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
            //textView.setText(jsonParser.urlBuider(user));
        }
    }

}
