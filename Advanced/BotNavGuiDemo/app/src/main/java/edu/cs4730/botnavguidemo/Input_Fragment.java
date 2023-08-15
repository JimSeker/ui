package edu.cs4730.botnavguidemo;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.botnavguidemo.databinding.InputFragmentBinding;

public class Input_Fragment extends Fragment implements OnClickListener {
    String TAG = "Input_fragment";
    InputFragmentBinding binding;
    public Input_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = InputFragmentBinding.inflate(inflater, container, false);

        binding.etPwd.addTextChangedListener(
            new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //doing nothing.
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // do nothing here.
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // When the text is changed.
                    Toast.makeText(requireContext(), binding.etPwd.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        );
        binding.button.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(requireContext(), binding.etSingle.getText(), Toast.LENGTH_LONG).show();
    }
}
