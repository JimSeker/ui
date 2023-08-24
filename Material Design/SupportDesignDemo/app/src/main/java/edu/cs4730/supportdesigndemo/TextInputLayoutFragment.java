package edu.cs4730.supportdesigndemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cs4730.supportdesigndemo.databinding.FragmentTextinputlayoutBinding;

/**
 * A simple example to show how to use the text input layout for a editText.
 * in the xml the textinputlayout is wrapped around the edittext.  it will display the hint
 * even after the user starts typing and allows you to set an error message as well.
 */
public class TextInputLayoutFragment extends Fragment {
    FragmentTextinputlayoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTextinputlayoutBinding.inflate(inflater, container, false);

        binding.edittext02.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("abc")) {
                    //set the error message that will display below the edittext
                    binding.textinput02.setError("Incorrect input.");
                } else {
                    binding.textinput02.setError("");  //clear the error message.
                }
            }

            //I don't care about this methods...
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        });

        return binding.getRoot();
    }


}
