package edu.cs4730.guidemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

import androidx.fragment.app.Fragment;

import edu.cs4730.guidemo.databinding.PickerFragmentBinding;

/**
 * shows how to use pickers.
 */
public class Picker_Fragment extends Fragment implements Button.OnClickListener {
    //used for the pickers to set the current time/date
    PickerFragmentBinding binding;
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
        binding = PickerFragmentBinding.inflate(inflater, container, false);
        binding.btnDate.setOnClickListener(this);
        binding.btnTime.setOnClickListener(this);

        //create the date picker listener, which is used later when the picker is called.
        d = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.tvDate.setText(fmtDate.format(dateAndTime.getTime()));
            }
        };
        //create the time picker listener
        t = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                dateAndTime.set(Calendar.HOUR, selectedHour);
                dateAndTime.set(Calendar.MINUTE, selectedMinute);
                // set current time into textview
                binding.tvTime.setText(fmtTime.format(dateAndTime.getTime()));
            }
        };
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

        if (v == binding.btnDate) {//date picker
            new DatePickerDialog(requireContext(), d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH)
            ).show();
        } else { //time picker
            Log.v("Time", "Should show the picker!");
            new TimePickerDialog(requireActivity(), t,
                    dateAndTime.get(Calendar.HOUR),
                    dateAndTime.get(Calendar.MINUTE),
                    false
            ).show();
        }
    }
}
