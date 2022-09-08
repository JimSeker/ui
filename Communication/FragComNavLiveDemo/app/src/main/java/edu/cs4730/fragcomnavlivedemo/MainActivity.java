package edu.cs4730.fragcomnavlivedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

/**
 * Uses Arch navigation to change between the fragments.
 * it also use a viewModel with MutableLiveData data for all three values, so any changes
 * can be updated via observers.
 */


public class MainActivity extends AppCompatActivity {

    DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get( DataViewModel.class);
    }
}
