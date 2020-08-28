package edu.cs4730.fragcomnavvmodeldemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

/**
 * simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 */

public class MainActivity extends AppCompatActivity {

    DataViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        mViewModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                //do something with the data
                Log.d("MainActivity", data);
            }
        });

    }
}
