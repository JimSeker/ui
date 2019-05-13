package edu.cs4730.botnavguidemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * This is basically code to show the constraintlayout with so many button.
 * the layout is very similar to the buttonfragment layout.
 */
public class ButtonCL_Fragment extends Fragment implements View.OnClickListener {

    String TAG = "Button_Fragment";
    Context myContext;
    TextView output;

    public ButtonCL_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.constraintlayout_fragment, container, false);
        //set it up so all the button work in this fragment.
        (myView.findViewById(R.id.button01)).setOnClickListener(this);
        (myView.findViewById(R.id.button02)).setOnClickListener(this);
        (myView.findViewById(R.id.button03)).setOnClickListener(this);
        (myView.findViewById(R.id.button04)).setOnClickListener(this);
        (myView.findViewById(R.id.button05)).setOnClickListener(this);
        (myView.findViewById(R.id.button06)).setOnClickListener(this);
        (myView.findViewById(R.id.button07)).setOnClickListener(this);
        (myView.findViewById(R.id.button08)).setOnClickListener(this);
        (myView.findViewById(R.id.button09)).setOnClickListener(this);
        (myView.findViewById(R.id.button10)).setOnClickListener(this);
        (myView.findViewById(R.id.button11)).setOnClickListener(this);
        //output to the screen.
        output = myView.findViewById(R.id.output);
        return myView;
    }


    /*
     * This on is the for the implements View.OnClickListener
     *
     */
    @Override
    public void onClick(View v) {
        Toast.makeText(myContext, "a button was clicked", Toast.LENGTH_SHORT).show();
        output.append("\na button was clicked");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myContext = context;
        Log.d(TAG, "onAttach");
    }


}
