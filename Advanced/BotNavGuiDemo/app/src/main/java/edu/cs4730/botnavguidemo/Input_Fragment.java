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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class Input_Fragment extends Fragment implements OnClickListener {
    String TAG = "Input_fragment";
    Context myContext;

    EditText et_single, et_mutli, et_pwd;
    Button btn;

    public Input_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "OnActivityCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.input_fragment, container, false);
        et_single = view.findViewById(R.id.et_single);
        et_pwd = view.findViewById(R.id.et_pwd);
        et_pwd.addTextChangedListener(
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
                    Toast.makeText(myContext, et_pwd.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        );
        btn = view.findViewById(R.id.button);
        btn.setOnClickListener(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myContext = context;
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(myContext, et_single.getText(), Toast.LENGTH_LONG).show();
    }
}
