package edu.cs4730.guidemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment

/**
 * This is an example with spinners and array Adapters.
 * Also a seek bar too for fun.
 *
 *
 * odd, the layout for the spinners are using a white text.  Normally they follow the theme.
 * Not sure what happened here.  But the text is hard to read.
 */
class Spinner_Fragment : Fragment(), AdapterView.OnItemSelectedListener {
    var TAG = "Spinner_fragment"
    lateinit var myContext: Context
    lateinit var SpinnerSB: Spinner
    lateinit var mySpinner: Spinner
    lateinit var mySeekBar: SeekBar
    lateinit var pb_cir: ProgressBar
    lateinit var pb_hor: ProgressBar
    lateinit var btn: Button

    //myList used to "fill" the first spinner.
    var myList = arrayOf("0", "1", "2", "3", "4", "5")

    //this is used when you are in a thread, and need to change a view/widget.
    private val handler = Handler { msg ->
        if (msg.what == 0) {  //message zero, which is enable the button again.
            btn!!.isEnabled = true
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.spinner_fragment, container, false)

        //first we will work on the spinner1 (which controls the seekbar)
        SpinnerSB = myView.findViewById(R.id.spinner1)
        //create the ArrayAdapter of strings from my List.
        val adapter = ArrayAdapter(myContext!!, android.R.layout.simple_spinner_item, myList)
        //set the dropdown layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //finally set the adapter to the spinner
        SpinnerSB.setAdapter(adapter)
        //set the selected listener as well
        SpinnerSB.setOnItemSelectedListener(this)

        //get the seekbar, no listener, the spinner will change it.
        mySeekBar = myView.findViewById(R.id.seekBar1)
        mySeekBar.setMax(5) //matches the items in myList.

        //finally the second spinner, but using the array from values strings.
        mySpinner = myView.findViewById(R.id.spinner2)
        //from the resource in values  /res/values/strings called spinneritems
        val adapter2 = ArrayAdapter.createFromResource(
            myContext!!, R.array.spinneritems,
            android.R.layout.simple_spinner_item
        )
        //now like before
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mySpinner.setAdapter(adapter2)
        mySpinner.setOnItemSelectedListener(this)
        pb_cir = myView.findViewById(R.id.progressBar)
        pb_hor = myView.findViewById(R.id.progressBar2)
        pb_hor.setMax(100)
        btn = myView.findViewById(R.id.prgbtn)
        btn.setOnClickListener(View.OnClickListener {
            btn.setEnabled(false)
            pb_hor.setProgress(0) //set it to zero before starting.
            Thread(progressUpdater()).start()
        })
        return myView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
        Log.d(TAG, "onAttach")
    }

    override fun onItemSelected(
        parent: AdapterView<*>, view: View, position: Int,
        id: Long
    ) {
        if (parent.id == R.id.spinner1) { // used to control the seekbar
            if (position != -1) //-1 is nothing selected.  just making sure.
                mySeekBar!!.progress =
                    position //just use position, don't care about the text itself themselves.
        } else {
            //this case, I want the text in the spinner box.
            Toast.makeText(
                myContext,
                parent.adapter.getItem(position).toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //Im ignoring this one.
    }

    /*
     *  A thread to update the progress bars.
     */
    internal inner class progressUpdater : Runnable {
        override fun run() {
            try {
                while (pb_hor!!.progress < pb_hor!!.max) {
                    Thread.sleep(1000) //1 second
                    pb_hor!!.incrementProgressBy(10)
                    pb_cir!!.incrementProgressBy(10)
                }
            } catch (Error: InterruptedException) {
                Error.printStackTrace()
            }
            // in the thread, (assuming handler is accessible to it) you can then send a message with
            handler.sendEmptyMessage(0) //where 0 is a message
        }
    }
}