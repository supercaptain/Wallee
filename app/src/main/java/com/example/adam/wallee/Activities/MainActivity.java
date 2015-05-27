package com.example.adam.wallee.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.adam.wallee.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.buttonStartActiviy);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.buttonCreateActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateTaskActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.buttonLoginActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.DateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DateActivity.class);
                startActivity(i);
            }
        });

        Toolbar t = (Toolbar)findViewById(R.id.app_bar);
        if(t != null){
            setSupportActionBar(t);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(getData());
        mRecyclerView.setAdapter(mAdapter);

    }



    public List<ItemList> getData(){
        ConnectivityManager conn = ( ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        List<ItemList> list = new ArrayList<ItemList>();
        /*String pro vypis TITULEK */
        String[] title =
                {"Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo",
                 "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku",
                 "Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku"};

        /*String pro vypis obsah */
        String[] content =
                {"Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku","Nakoupit jidlo", "Zavolat doktorovi", "Sehnat nahradni baterku"};

        for(int i = 0; i < title.length; i++){
            ItemList curr = new ItemList();
            curr.title = title[i];
            curr.content = content[i];
            list.add(curr);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.action_settings2) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
