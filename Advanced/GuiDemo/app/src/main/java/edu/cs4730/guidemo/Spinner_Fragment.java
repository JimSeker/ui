package edu.cs4730.guidemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.guidemo.databinding.SpinnerFragmentBinding;


/**
 * This is an example with spinners and array Adapters.
 * Also a seek bar just for fun.
 */
public class Spinner_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {


    String TAG = "Spinner_fragment";
    SpinnerFragmentBinding binding;
    //myList used to "fill" the first spinner.
    String[] myList = {"0", "1", "2", "3", "4", "5"};

    //this is used when you are in a thread, and need to change a view/widget.
    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = SpinnerFragmentBinding.inflate(inflater, container, false);

        //first we will work on the spinner1 (which controls the seekbar)
        //create the ArrayAdapter of strings from my List.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, myList);
        //set the dropdown layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //finally set the adapter to the spinner
        binding.spinnerSB.setAdapter(adapter);
        //set the selected listener as well
        binding.spinnerSB.setOnItemSelectedListener(this);

        //get the seekbar, no listener, the spinner will change it.
        binding.mySeekBar.setMax(5);  //matches the items in myList.

        //finally the second spinner, but using the array from values strings.;
        //from the resource in values  /res/values/strings called spinneritems
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(requireContext(), R.array.spinneritems,
                android.R.layout.simple_spinner_item);
        //now like before
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.mySpinner.setAdapter(adapter2);
        binding.mySpinner.setOnItemSelectedListener(this);

        //setup the progress bars.
        binding.pbHor.setMax(100);
        binding.prgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.prgbtn.setEnabled(false);
                binding.pbHor.setProgress(0);  //set it to zero before starting.
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

        if (parent.getId() == R.id.spinnerSB) { // used to control the seekbar
            if (position != -1)  //-1 is nothing selected.  just making sure.
                binding.mySeekBar.setProgress(position);  //just use position, don't care about the text itself themselves.
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
                while (binding.pbHor.getProgress() < binding.pbHor.getMax()) {
                    Thread.sleep(1000);  //1 second
                    binding.pbHor.incrementProgressBy(10);
                    binding.pbCir.incrementProgressBy(10);
                }
            } catch (InterruptedException Error) {
                Error.printStackTrace();
            }
            // in the thread, (assuming handler is accessible to it) you can then send a message with
            handler.sendEmptyMessage(0);  //where 0 is a message
        }
    }
}
