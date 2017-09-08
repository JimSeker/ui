package edu.cs4730.fragformexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
 * simple example with a fragment.  MainActivity here is basically here to display the fragment
 * and nothing else.
 */


public class MainActivity extends AppCompatActivity {

    FormFragment myFormFragment;
    //variable for the log
    String TAG = "FormFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            myFormFragment = new FormFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, myFormFragment).commit();
        }
    }
}
