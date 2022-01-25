package edu.cs4730.botnavguidemo_kt

import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.DateFormat
import java.util.Calendar

/**
 * A simple [Fragment] subclass.
 */
class Picker_Fragment : Fragment(), View.OnClickListener {
    lateinit var tv_date: TextView
    lateinit var tv_time: TextView
    lateinit var btn_date: Button
    lateinit var btn_time: Button

    //used for the pickers to set the current time/date
    var dateAndTime = Calendar.getInstance()
    var fmtDate = DateFormat.getDateInstance()
    var fmtTime = DateFormat.getTimeInstance()

    //listeners for the data and time picker.
    var d: OnDateSetListener? = null
    var t: OnTimeSetListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.picker_fragment, container, false)
        tv_date = myView.findViewById(R.id.tv_date)
        tv_time = myView.findViewById(R.id.tv_time)
        btn_date = myView.findViewById(R.id.btn_date)
        btn_date.setOnClickListener(this)
        btn_time = myView.findViewById(R.id.btn_time)
        btn_time.setOnClickListener(this)

        //create the date picker listener, which is used later when the picker is called.
        d = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateAndTime[Calendar.YEAR] = year
            dateAndTime[Calendar.MONTH] = monthOfYear
            dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
            tv_date.setText(fmtDate.format(dateAndTime.time))
        }
        //create the time picker listener
        t = OnTimeSetListener { view, selectedHour, selectedMinute ->
            dateAndTime[Calendar.HOUR] = selectedHour
            dateAndTime[Calendar.MINUTE] = selectedMinute
            // set current time into textview
            tv_time.setText(fmtTime.format(dateAndTime.time))
        }
        return myView
    }

    override fun onClick(v: View) {
        if (v === btn_date) { //date picker
            DatePickerDialog(
                requireActivity(), d,
                dateAndTime[Calendar.YEAR],
                dateAndTime[Calendar.MONTH],
                dateAndTime[Calendar.DAY_OF_MONTH]
            ).show()
        } else { //time picker
            Log.v("Time", "Should show the picker!")
            TimePickerDialog(
                activity, t,
                dateAndTime[Calendar.HOUR],
                dateAndTime[Calendar.MINUTE],
                false
            ).show()
        }
    }
}