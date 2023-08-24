package edu.cs4730.simpledemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import edu.cs4730.simpledemo.databinding.ActivityMainBinding;

/*
  This is all default code.  Look at the styles.xml and colors.xml for the
  material design color style.
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
