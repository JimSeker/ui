package edu.cs4730.fragcomnavvmodeldemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import edu.cs4730.fragcomnavvmodeldemo.databinding.ActivityMainBinding;

/**
 * simple example use a AndroidviewModel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 * <p>
 * the numbers are not live data, so no observers.  those are set "manually".  while the string data item is using mutable live data
 * so an observer can be used to update the data whenever it changes.
 */

public class MainActivity extends AppCompatActivity {
    DataViewModel mViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
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
