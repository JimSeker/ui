package edu.cs4730.botnavguidemo_kt

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import edu.cs4730.botnavguidemo_kt.databinding.PickerFragmentBinding
import java.text.DateFormat
import java.util.Calendar

/**
 * example of pickers.
 */
class Picker_Fragment : Fragment(), View.OnClickListener {
    lateinit var binding: PickerFragmentBinding

    //used for the pickers to set the current time/date
    var dateAndTime = Calendar.getInstance()
    var fmtDate = DateFormat.getDateInstance()
    var fmtTime = DateFormat.getTimeInstance()

    //listeners for the data and time picker.
    lateinit var d: OnDateSetListener
    lateinit var t: OnTimeSetListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        binding = PickerFragmentBinding.inflate(inflater, container, false)
        binding.btnDate.setOnClickListener(this)
        binding.btnTime.setOnClickListener(this)

        //create the date picker listener, which is used later when the picker is called.
        d = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateAndTime[Calendar.YEAR] = year
            dateAndTime[Calendar.MONTH] = monthOfYear
            dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
            binding.tvDate.text = fmtDate.format(dateAndTime.time)
        }
        //create the time picker listener
        t = OnTimeSetListener { view, selectedHour, selectedMinute ->
            dateAndTime[Calendar.HOUR] = selectedHour
            dateAndTime[Calendar.MINUTE] = selectedMinute
            // set current time into textview
            binding.tvTime.text = fmtTime.format(dateAndTime.time)
        }
        return binding.root
    }

    override fun onClick(v: View) {
        if (v === binding.btnDate) { //date picker
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