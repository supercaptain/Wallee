package com.example.adam.wallee.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Adam on 27.3.2015.
 */
public class AlarmService extends Service  {
    Calendar calendar;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,22);
            calendar.set(Calendar.MINUTE,48);
            calendar.set(Calendar.SECOND, 00);


        Context context = getApplicationContext();
        Intent myIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        return START_NOT_STICKY;
    }
}
