package edu.cs4730.fragcomnavlivedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import edu.cs4730.fragcomnavlivedemo.databinding.ActivityMainBinding;

/**
 * Uses Arch navigation to change between the fragments.
 * it also use a viewModel with MutableLiveData data for all three values, so any changes
 * can be updated via observers.
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
        mViewModel = new ViewModelProvider(this).get( DataViewModel.class);
    }
}
