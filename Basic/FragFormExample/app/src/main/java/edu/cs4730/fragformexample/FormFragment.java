package edu.cs4730.fragformexample;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;

import androidx.fragment.app.Fragment;

import edu.cs4730.fragformexample.databinding.FragmentFormBinding;

/**
 * The meat of the example is here, instead of the mainActivity.  OnCreateView has the setup
 * and then all the listeners.
 */
public class FormFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, TextWatcher,
        Button.OnClickListener {

    private FragmentFormBinding binding;

    //variables for the widgets
    //RadioGroup myRadioGroup;
    //EditText et;
    //Button btnalert;
    //TextView label;
    //variable for the log
    String TAG = "FormFragment";

    public FormFragment() {
        // Required empty public constructor
    }

    //OnCreateView is where everything is inflated and any listeners are setup at.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(inflater, container, false);

        //EditText view setup and listner
        //et = myView.findViewById(R.id.ETname);
        binding.ETname.addTextChangedListener(this);

        //the top label in the xml doc.
        //label = myView.findViewById(R.id.Label01);

        //setup the radio group with a listener.
        //myRadioGroup = myView.findViewById(R.id.myRadioGroup);
        binding.myRadioGroup.setOnCheckedChangeListener(this);

        //setup the button with a listener as well.
        //btnalert = myView.findViewById(R.id.btnalert);
        binding.btnalert.setOnClickListener(this);

        return binding.getRoot();
    }


    /*  Radio group listener for OnCheckedChangeListener */
    public void onCheckedChanged(RadioGroup group, int CheckedId) {
        if (group == binding.myRadioGroup) { //if not myRadioGroup, we are in trouble!
            if (CheckedId == R.id.RB01) {
                // information radio button clicked
                Log.d(TAG, "RB01 was pushed.");
            } else if (CheckedId == R.id.RB02) {
                // Confirmation radio button clicked
                Log.d(TAG, "RB02 was pushed.");
            } else if (CheckedId == R.id.RB03) {
                // Warning radio button clicked
                Toast.makeText(getActivity(), "Warning!", Toast.LENGTH_LONG).show();
            }
        }
    }

    /* EditView listeners */
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (binding.ETname.length() > 10) {
            Toast.makeText(getActivity(), "Long Word!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Long Word!");
        }
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //left blank
    }

    public void afterTextChanged(Editable s) {
        //left blank
    }

    /* button listener */
    public void onClick(View v) {
        if (v == binding.btnalert) {
            if (binding.ETname.getText().toString().compareTo("") != 0)
                Toast.makeText(requireContext(), "The edittext has " + binding.ETname.getText().toString(), Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(requireContext(), "The button was pressed", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "The button was pressed.");
        }
    }
}
