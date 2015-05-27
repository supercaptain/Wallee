package com.example.adam.wallee.Activities;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adam.wallee.JsonParser;
import com.example.adam.wallee.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CreateTaskActivity extends ActionBarActivity {

    public Button btnCreate;
    public EditText edtTitle;
    public EditText edtContent;
    public TextView txtResult;

    JsonParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        edtTitle = (EditText) findViewById(R.id.editText_Title);
        edtContent = (EditText) findViewById(R.id.editText_Content);
        btnCreate = (Button) findViewById(R.id.button_Createtask);
        txtResult = (TextView) findViewById(R.id.textViewResult);

        jsonParser = new JsonParser(this);

        Toolbar t = (Toolbar)findViewById(R.id.app_bar);
        if(t != null){
            setSupportActionBar(t);
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Uplouder().execute(composerTaskObject());
                txtResult.setText(composerTask());
            }
        });
    }

    public String composerTask(){

        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();

        JSONObject task = new JSONObject();
        try{
            task.put(JsonParser.TAG_TASK,title);
            task.put(JsonParser.TAG_STATUS,content);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        return task.toString();

    }

    public JSONObject composerTaskObject(){

        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();

        JSONObject task = new JSONObject();
        try{
            task.put(JsonParser.TAG_TASK,title);
            task.put(JsonParser.TAG_STATUS,content);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return task;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_task, menu);
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


    class Uplouder extends  AsyncTask<JSONObject,Void, String>{

        @Override
        protected String doInBackground(JSONObject... params) {
            String s = null;
            try {
                jsonParser.sendDataToServer(params[0]);
            }
            catch (IOException e){
            }
            return  s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtResult.setText(s);
        }
    }
}
