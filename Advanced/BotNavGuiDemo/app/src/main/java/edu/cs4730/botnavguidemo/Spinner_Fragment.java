package edu.cs4730.botnavguidemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.botnavguidemo.databinding.SpinnerFragmentBinding;


/**
 * This is an example with spinners and array Adapters.
 * Also a seek bar too for fun.
 * <p>
 * odd, the layout for the spinners are using a white text.  Normally they follow the theme.
 * Not sure what happened here.  But the text is hard to read.
 */
public class Spinner_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {
    String TAG = "Spinner_fragment";
    //    Context myContext;
//    Spinner SpinnerSB, mySpinner;
//    SeekBar mySeekBar;
//    ProgressBar pb_cir, pb_hor;
//    Button btn;
    SpinnerFragmentBinding binding;
    //myList used to "fill" the first spinner.
    String[] myList = {"0", "1", "2", "3", "4", "5"};

    //this is used when you are in a thread, and need to change a view/widget.
    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {  //message zero, which is enable the button again.
                binding.prgbtn.setEnabled(true);
            }
            return true;
        }
    });

    public Spinner_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = SpinnerFragmentBinding.inflate(inflater, container, false);

        //first we will work on the spinner1 (which controls the seekbar)
        //create the ArrayAdapter of strings from my List.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, myList);
        //set the dropdown layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //finally set the adapter to the spinner
        binding.spinner1.setAdapter(adapter);
        //set the selected listener as well
        binding.spinner1.setOnItemSelectedListener(this);

        //get the seekbar, no listener, the spinner will change it.
        binding.seekBar1.setMax(5);  //matches the items in myList.

        //finally the second spinner, but using the array from values strings.;
        //from the resource in values  /res/values/strings called spinneritems
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(requireContext(), R.array.spinneritems,
                android.R.layout.simple_spinner_item);
        //now like before
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner2.setAdapter(adapter2);
        binding.spinner2.setOnItemSelectedListener(this);

        //setup the progress bars.
        binding.progressBar2.setMax(100);
        binding.prgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.prgbtn.setEnabled(false);
                binding.progressBar2.setProgress(0);  //set it to zero before starting.
                new Thread(new progressUpdater()).start();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.spinner1) { // used to control the seekbar
            if (position != -1)  //-1 is nothing selected.  just making sure.
                binding.seekBar1.setProgress(position);  //just use position, don't care about the text itself themselves.
        } else {
            //this case, I want the text in the spinner box.
            Toast.makeText(requireContext(), parent.getAdapter().getItem(position).toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Im ignoring this one.
    }

    /*
     *  A thread to update the progress bars.
     */
    class progressUpdater implements Runnable {
        @Override
        public void run() {
            try {
                while (binding.progressBar2.getProgress() < binding.progressBar2.getMax()) {
                    Thread.sleep(1000);  //1 second
                    binding.progressBar2.incrementProgressBy(10);
                    binding.progressBar.incrementProgressBy(10);
                }
            } catch (InterruptedException Error) {
                Error.printStackTrace();
            }
            // in the thread, (assuming handler is accessible to it) you can then send a message with
            handler.sendEmptyMessage(0);  //where 0 is a message
        }
    }
}
