package edu.cs4730.supportdesigndemo;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple example to show how to use the text input layout for a editText.
 * in the xml the textinputlayout is wrapped around the edittext.  it will display the hint
 * even after the user starts typing and allows you to set an error message as well.
 */
public class TextInputLayoutFragment extends Fragment {

    EditText et1, et2;
    TextInputLayout mTextInputLayout;
    public TextInputLayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_textinputlayout, container, false);
        et1 = (EditText) myView.findViewById(R.id.edittext01);

        mTextInputLayout = (TextInputLayout) myView.findViewById(R.id.textinput02);
        et2 = (EditText) myView.findViewById(R.id.edittext02);
        et2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("abc")) {
                    //set the error message that will display below the edittext
                    mTextInputLayout.setError("Incorrect input.");
                } else {
                    mTextInputLayout.setError("");  //clear the error message.
                }
            }
            //I don't care about this methods...
            @Override
            public void afterTextChanged(Editable s) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        });

        return myView;
    }


}
