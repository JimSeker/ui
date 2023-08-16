package edu.cs4730.guidemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.guidemo.databinding.RadiocheckFragmentBinding;

/**
 * Example of Radio button and check box.
 */
public class RadioCheck_Fragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    String TAG = "RadioCheck_Fragment";

    RadiocheckFragmentBinding binding;

    public RadioCheck_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = RadiocheckFragmentBinding.inflate(inflater, container, false);

        //setup the radiogroup.
        //listener is onCheckedChanged and implemented from class level.
        binding.radioGroup1.setOnCheckedChangeListener(this);

        //setup the check box.
        binding.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //We know which checkbox, so ignoring buttonView variable.
                if (isChecked) {
                    binding.checkBox1.setText("Uncheck me and I'll clear the raido Buttons too!");
                } else {
                    binding.radioGroup1.clearCheck();  //clears any radio buttons.
                    binding.checkBox1.setText("Please Check me!");
                }
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group == binding.radioGroup1) { //if not, we are in trouble!
            if (checkedId == R.id.radio0) {
                // information radio button clicked
                Toast.makeText(requireContext(), "Radio Button 0 checked.", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radio1) {
                // Confirmation radio button clicked
                Toast.makeText(requireContext(), "Radio Button 1 checked.", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radio2) {
                //radio button 2 clicked
                Toast.makeText(requireContext(), "Radio Button 2 checked.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
