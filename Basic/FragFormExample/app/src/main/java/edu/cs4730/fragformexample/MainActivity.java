package edu.cs4730.fragformexample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        ViewCompat.setOnApplyWindowInsetsListener(binding.container, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (savedInstanceState == null) {
            myFormFragment = new FormFragment();
            getSupportFragmentManager().beginTransaction().add(binding.container.getId(), myFormFragment).commit();
        }
    }
}
