package com.example.adam.wallee.Activities;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.adam.wallee.Alarm.DateFragment;
import com.example.adam.wallee.Alarm.TimeFragment;
import com.example.adam.wallee.Libs.SlidingTabLayout;
import com.example.adam.wallee.R;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment{


    private SlidingTabLayout slideTab;
    private ViewPager viewPager;
    TabHost tabs;
    private DialogAdapter pageAdapter;
    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    public OnDateTimeSetListener mOnDateTimeSetListener;
    private ButtonClickListener mButtonClickListener;
    Context context;

    public  TimePickerFragment(){}





    public interface OnDateTimeSetListener{
        public void DateTimeSet(String string);
    }
    /*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,DateFormat.is24HourFormat(getActivity()));
    }
    */


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
        mButtonClickListener = new ButtonClickListener();

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle("Vyber datum a čas ");

        //builder.setView(createView(inflater));
        builder.setPositiveButton(R.string.ok, mButtonClickListener);
        builder.setNegativeButton(R.string.del, mButtonClickListener);
        AlertDialog dialog = builder.create();
        dialog.setView(createView(inflater));

        return  dialog;
    }

    public View createView(LayoutInflater inflater){

        View view = inflater.inflate(R.layout.date_dialog, null);
        tabs=(TabHost) view.findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec tabpage1 = tabs.newTabSpec("Date");
        tabpage1.setContent(R.id.shareIndividual);
        tabpage1.setIndicator("Date");
        tabs.addTab(tabpage1);

        TabHost.TabSpec tabpage2 = tabs.newTabSpec("two");
        tabpage2.setContent(R.id.shareGroup);
        tabpage2.setIndicator("Time");
        tabs.addTab(tabpage2);

        mDatePicker = (DatePicker) view.findViewById(R.id.date_picker);
        mTimePicker = (TimePicker) view.findViewById(R.id.time_picker);
        Calendar c = Calendar.getInstance();
        mTimePicker.setIs24HourView(true);
        mTimePicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        mTimePicker.setCurrentMinute(c.get(Calendar.MINUTE));


        return view;

    }

    private class ButtonClickListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialogInterface, int result) {
            if(DialogInterface.BUTTON_POSITIVE == result){
                //mOnDateTimeSetListener.DateTimeSet("aqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                //Toast.makeText(context, "Hi,s ffffffffffffffffffffffffffffff", Toast.LENGTH_SHORT).show();
                OnDateTimeSetListener activity = (OnDateTimeSetListener)getActivity();
                activity.DateTimeSet("test po kliknuti");

            }
        }

    }


    class DialogAdapter extends FragmentPagerAdapter {

        public DialogAdapter(FragmentManager fm) {super(fm); }
        @Override
        public int getCount() {return 2;}

        @Override
        public CharSequence getPageTitle(int i) {
            switch (i) {
                case 0:
                    return "Date";
                case 1:
                    return "Time";
            }
            return null;
        }


        @Override
        public Fragment getItem(int i) {
           Fragment f = null;
            if(i == 0)
                f = new  TimePickerFragment();
            if(i == 1)
                f = new DateFragment();
            return f;
        }
    }

}