package com.example.adam.wallee.Activities;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.adam.wallee.Alarm.AlarmReceiver;
import com.example.adam.wallee.Alarm.AlarmService;
import com.example.adam.wallee.R;

import java.util.Calendar;
import java.util.Date;

public class DateActivity extends ActionBarActivity  implements TimePickerFragment.OnDateTimeSetListener{

    private TimePicker timePicker1;
    private Calendar calendar;
    private TextView textView;
    private TextView text;
    private Button button;
    private Button btn;
    int hour;
    int min;
    TimePickerFragment newFragment;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        button = (Button)findViewById(R.id.setButton);
        btn = (Button)findViewById(R.id.starTime);
        textView = (TextView) findViewById(R.id.timer);
        text = (TextView) findViewById(R.id.dater);
        textView.setText("saaaaa");
        newFragment = new TimePickerFragment();
        //newFragment.mOnDateTimeSetListener = this;
        context = this;
        /*
        timePicker1 = (TimePicker) findViewBgyId(R.id.timePicker1);
        textView = (TextView) findViewById(R.id.timer);
        button = (Button)findViewById(R.id.setButton);
        timePicker1.setIs24HourView(true);
        calendar = Calendar.getInstance();
        hour = timePicker1.getCurrentHour();
        min = timePicker1.getCurrentMinute();
        textView.setText(hour + " : " + min);
        */

        calendar = Calendar.getInstance();

        /*
            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH, 3);
       */

        calendar.set(Calendar.YEAR,2015);
        calendar.set(Calendar.MONTH,3);
        calendar.set(Calendar.HOUR_OF_DAY,21);
        calendar.set(Calendar.MINUTE, 3);
        calendar.set(Calendar.SECOND, 00);


        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);

        long time = calendar.getTimeInMillis();

        textView.setText( h + " " + m + " " + s );
        text.setText( "Time ---  " + time );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                newFragment.show(getSupportFragmentManager(), "timepicker");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Kliknul si na start", Toast.LENGTH_LONG).show();
                startService(new Intent(context, AlarmService.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date, menu);
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

    @Override
    public void DateTimeSet(String string) {
        Calendar c = Calendar.getInstance();
        textView.setText("cest");
        Toast.makeText(this, "Hi," + string , Toast.LENGTH_SHORT).show();
    }
}
