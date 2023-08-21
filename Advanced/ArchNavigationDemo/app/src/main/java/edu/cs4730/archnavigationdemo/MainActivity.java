package edu.cs4730.archnavigationdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import edu.cs4730.archnavigationdemo.databinding.ActivityMainBinding;

/**
 * Nothing really to see here.  It's all about the fragments and Navigation demo.
 * This is handled by the layout and res/navigation.
 */

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
