package edu.cs4730.fragformexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.cs4730.fragformexample.databinding.ActivityMainBinding;

/*
 * simple example with a fragment.  MainActivity here is basically here to display the fragment
 * and nothing else.
 */

public class MainActivity extends AppCompatActivity {

    FormFragment myFormFragment;
    private ActivityMainBinding binding;
    //variable for the log
    String TAG = "FormFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            myFormFragment = new FormFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, myFormFragment).commit();
        }
    }
}
