package edu.cs4730.guidemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.guidemo.databinding.ConstraintlayoutFragmentBinding;

/**
 * This is basically code to show the constraintlayout with so many button.
 * the layout is very similar to the buttonfragment layout.
 */
public class ButtonCL_Fragment extends Fragment implements View.OnClickListener {

    String TAG = "Button_Fragment";
    ConstraintlayoutFragmentBinding binding;

    public ButtonCL_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ConstraintlayoutFragmentBinding.inflate(inflater, container, false);
        //set it up so all the button work in this fragment.
        binding.button01.setOnClickListener(this);
        binding.button02.setOnClickListener(this);
        binding.button03.setOnClickListener(this);
        binding.button04.setOnClickListener(this);
        binding.button05.setOnClickListener(this);
        binding.button06.setOnClickListener(this);
        binding.button07.setOnClickListener(this);
        binding.button08.setOnClickListener(this);
        binding.button09.setOnClickListener(this);
        binding.button10.setOnClickListener(this);
        binding.button11.setOnClickListener(this);
        return binding.getRoot();
    }

    /**
     * This on is the for the implements View.OnClickListener
     */
    @Override
    public void onClick(View v) {
        Toast.makeText(requireContext(), "a button was clicked", Toast.LENGTH_SHORT).show();
        binding.output.append("\na button was clicked");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }
}
