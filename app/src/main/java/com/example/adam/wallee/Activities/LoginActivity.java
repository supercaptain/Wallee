package com.example.adam.wallee.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adam.wallee.JsonParser;
import com.example.adam.wallee.Model.IJsonManager;
import com.example.adam.wallee.Model.JsonManager;
import com.example.adam.wallee.Model.UserModel;
import com.example.adam.wallee.R;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class LoginActivity extends ActionBarActivity{

    private EditText emailTxt;
    private EditText passTxt;
    private Button loginBtn;
    private TextView apiTxt;

    private UserModel user;
    private JsonManager jsonManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.init();
        Toolbar t = (Toolbar)findViewById(R.id.app_bar);
        if(t != null){
            setSupportActionBar(t);
            setTitle("Sign In");
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailTxt.getText().toString();
                String password = passTxt.getText().toString();

                //From POST request you get apikey for interactivity contact with backend
                new LoginUser().execute(jsonManager.createJsonObjectLogin(email,password));
                Log.i("LOGIN", "Cliker  -- " + user.getApiKey() );

                /*
                if(user.getApiKey() != null) {
                    Log.i("LOGIN", "Iffer -- " + user.getApiKey() );
                    Intent intent = new Intent(LoginActivity.this, DownloadActivity.class);
                    intent.putExtra("User", user);
                    startActivity(intent);
                }*/
            }
        });
    }

    public void init(){
        emailTxt = (EditText)findViewById(R.id.emailIntup);
        passTxt = (EditText)findViewById(R.id.passwordInput);
        loginBtn = (Button)findViewById(R.id.loginButton);
        apiTxt = (TextView)findViewById(R.id.apiTextView);
        jsonManager = new JsonManager();
        user = new UserModel();
    }

    class LoginUser extends AsyncTask<JSONObject, Void, String>{

        JsonParser jsonParser;
        LoginUser() {
            jsonParser = new JsonParser(getBaseContext());
        }

        @Override
        protected String doInBackground(JSONObject... params) {
            try {
                String resText;
                HttpResponse response = jsonParser.sendDataToServerLogin(params[0]);
                resText = EntityUtils.toString(response.getEntity());
                JSONArray resObj = new JSONArray(resText);
                JSONObject o = resObj.getJSONObject(0);
                String test = o.getString("api_key");
                Log.i("LOGIN", "Test  -- " + test );
                return test;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result){
            apiTxt.setText('c' + result);
            user.setApiKey(result);

            if(user.getApiKey() != null) {
                Log.i("LOGIN", "Iffer -- " + user.getApiKey() );
                Intent intent = new Intent(LoginActivity.this, DownloadActivity.class);
                intent.putExtra("User", user);

                startActivity(intent);
            }
        }

        protected void onProgressUpdate(Void... progress) {

        }

    }






}
