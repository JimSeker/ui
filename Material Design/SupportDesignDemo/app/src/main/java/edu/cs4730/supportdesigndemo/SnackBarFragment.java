package edu.cs4730.supportdesigndemo;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class SnackBarFragment extends Fragment implements  View.OnClickListener{

    Button btn;
    View myView;

    public SnackBarFragment() {
    }
    View.OnClickListener SBonClickListener = new View.OnClickListener(){
        public void  onClick  (View  v){
            Toast.makeText(getActivity(),"You clicked undo", Toast.LENGTH_LONG).show();
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_snackbar, container, false);
        btn = (Button) myView.findViewById(R.id.button01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(myView, "Hi there?", Snackbar.LENGTH_LONG)
                        .setAction("Undo?", SBonClickListener)
                        .show();
            }
        });
        return myView;
    }

    //click listener for the snackbar button.
    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),"You clicked undo", Toast.LENGTH_LONG).show();
    }
}
