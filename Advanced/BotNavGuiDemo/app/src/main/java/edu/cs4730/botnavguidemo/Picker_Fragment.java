package edu.cs4730.botnavguidemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Picker_Fragment extends Fragment implements Button.OnClickListener {
    TextView tv_date, tv_time;
    Button btn_date, btn_time;
    //used for the pickers to set the current time/date
    Calendar dateAndTime = Calendar.getInstance();
    DateFormat fmtDate = DateFormat.getDateInstance();
    DateFormat fmtTime = DateFormat.getTimeInstance();
    //listeners for the data and time picker.
    DatePickerDialog.OnDateSetListener d;
    TimePickerDialog.OnTimeSetListener t;

    public Picker_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.picker_fragment, container, false);
        tv_date = myView.findViewById(R.id.tv_date);
        tv_time = myView.findViewById(R.id.tv_time);
        btn_date = myView.findViewById(R.id.btn_date);
        btn_date.setOnClickListener(this);
        btn_time = myView.findViewById(R.id.btn_time);
        btn_time.setOnClickListener(this);

        //create the date picker listener, which is used later when the picker is called.
        d = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tv_date.setText(fmtDate.format(dateAndTime.getTime()));
            }
        };
        //create the time picker listener
        t = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                dateAndTime.set(Calendar.HOUR, selectedHour);
                dateAndTime.set(Calendar.MINUTE, selectedMinute);
                // set current time into textview
                tv_time.setText(fmtTime.format(dateAndTime.getTime()));
            }
        };
        return myView;
    }


    @Override
    public void onClick(View v) {

        if (v == btn_date) {//date picker
            new DatePickerDialog(getActivity(), d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
            ).show();
        } else { //time picker
            Log.v("Time", "Should show the picker!");
            new TimePickerDialog(getActivity(), t,
                dateAndTime.get(Calendar.HOUR),
                dateAndTime.get(Calendar.MINUTE),
                false
            ).show();
        }
    }
}
