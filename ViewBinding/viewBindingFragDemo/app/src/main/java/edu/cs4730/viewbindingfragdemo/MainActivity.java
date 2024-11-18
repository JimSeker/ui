package edu.cs4730.viewbindingfragdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;

import edu.cs4730.viewbindingfragdemo.databinding.ActivityMainBinding;

/**
 * a very simple example of using viewbinding with fragment.  The variables names are the id names in the xml.
 * the file, is the camel case version of the xml file name (only letters, cap'd for each word) with the
 * Binding appended to the name.  Note, this is NOT dataBinding.
 * <p>
 * viewbinding allows you to skip all the findviewbyid calls and you should not have any null pointer issues
 * since the binding variable is used for everything.
 * note it must be turned on in the build.gradle file.
 * <p>
 * In this example see the fragments.  nothing here to uses viewBinding.
 */

public class MainActivity extends AppCompatActivity implements OneFragment.OnFragmentInteractionListener1 {

    private ActivityMainBinding binding;

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
        getSupportFragmentManager().beginTransaction().add(binding.container.getId(), new OneFragment()).commit();

    }

    @Override
    public void onFragmentInteraction1(String Data) {
        getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), new TwoFragment())
            .addToBackStack(null).commit();
    }
}