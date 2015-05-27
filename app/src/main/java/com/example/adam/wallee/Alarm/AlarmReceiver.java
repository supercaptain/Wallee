package com.example.adam.wallee.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

import com.example.adam.wallee.Activities.AlarmActivity;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Aktivita se spousti", Toast.LENGTH_LONG).show();
        Intent mathAlarmAlertActivityIntent;
        mathAlarmAlertActivityIntent = new Intent(context, AlarmActivity.class);
        mathAlarmAlertActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mathAlarmAlertActivityIntent);

    }
}
