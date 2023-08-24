package edu.cs4730.supportdesigndemo2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.cs4730.supportdesigndemo2.databinding.MiddleBinding;

/**
 * This is the middle fragment, which uses a edittext to put text on the either the left or right
 * fragment in the view pager. It uses a modelview to start the data so the fragments can get via
 * an observer.
 */

public class FragMid extends Fragment implements Button.OnClickListener {
    MiddleBinding binding;
    DataViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = MiddleBinding.inflate(inflater, container, false);
        binding.btnLt.setOnClickListener(this);
        binding.btnRt.setOnClickListener(this);
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v == binding.btnLt) { //left button
            mViewModel.appendStrLeft(binding.editText1.getText().toString());
        } else {  //right button
            mViewModel.appendStrRight(binding.editText1.getText().toString());
        }
        binding.editText1.setText("");
    }
}
