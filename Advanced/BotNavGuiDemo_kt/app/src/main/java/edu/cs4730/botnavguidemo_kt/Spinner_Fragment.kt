package edu.cs4730.botnavguidemo_kt

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.cs4730.botnavguidemo_kt.databinding.SpinnerFragmentBinding

/**
 * This is an example with spinners and array Adapters.
 * Also a seek bar too for fun.
 *
 * odd, the layout for the spinners are using a white text.  Normally they follow the theme.
 * Not sure what happened here.  But the text is hard to read.
 */
class Spinner_Fragment : Fragment(), AdapterView.OnItemSelectedListener {
    var TAG = "Spinner_fragment"
    lateinit var binding: SpinnerFragmentBinding

    //myList used to "fill" the first spinner.
    var myList = arrayOf("0", "1", "2", "3", "4", "5")

    //this is used when you are in a thread, and need to change a view/widget.
    private val handler: Handler = Handler { msg ->
        if (msg.what == 0) {  //message zero, which is enable the button again.
            binding.prgbtn.isEnabled = true
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
    ): View {
        // Inflate the layout for this fragment
        binding = SpinnerFragmentBinding.inflate(inflater, container, false)

        //first we will work on the spinner1 (which controls the seekbar)
        //create the ArrayAdapter of strings from my List.
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, myList)
        //set the dropdown layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //finally set the adapter to the spinner
        binding.spinnerSB.adapter = adapter
        //set the selected listener as well
        binding.spinnerSB.onItemSelectedListener = this

        //get the seekbar, no listener, the spinner will change it.
        binding.mySeekBar.max = 5 //matches the items in myList.

        //finally the second spinner, but using the array from values strings.;
        //from the resource in values  /res/values/strings called spinneritems
        val adapter2 = ArrayAdapter.createFromResource(
            requireContext(), R.array.spinneritems,
            android.R.layout.simple_spinner_item
        )
        //now like before
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mySpinner.adapter = adapter2
        binding.mySpinner.onItemSelectedListener = this

        //setup the progress bars.
        binding.pbHor.max = 100
        binding.prgbtn.setOnClickListener {
            binding.prgbtn.isEnabled = false
            binding.pbHor.progress = 0 //set it to zero before starting.
            Thread(progressUpdater()).start()
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onItemSelected(
        parent: AdapterView<*>, view: View, position: Int,
        id: Long
    ) {
        if (parent.id == R.id.spinnerSB) { // used to control the seekbar
            if (position != -1) //-1 is nothing selected.  just making sure.
                binding.mySeekBar.progress =
                    position //just use position, don't care about the text itself themselves.
        } else {
            //this case, I want the text in the spinner box.
            Toast.makeText(
                requireContext(),
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
                while (binding.pbHor.progress < binding.pbHor.max) {
                    Thread.sleep(1000) //1 second
                    binding.pbHor.incrementProgressBy(10)
                    binding.pbCir.incrementProgressBy(10)
                }
            } catch (Error: InterruptedException) {
                Error.printStackTrace()
            }
            // in the thread, (assuming handler is accessible to it) you can then send a message with
            handler.sendEmptyMessage(0) //where 0 is a message
        }
    }
}