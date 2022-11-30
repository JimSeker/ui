package edu.cs4730.viewbindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import edu.cs4730.viewbindingdemo.databinding.ActivityMainBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * a very simple example of using viewbinding.  The variables names are the id names in the xml.
 * the file, is the camel case version of the xml file name (only letters, cap'd for each word) with the
 * Binding appended to the name.  Note, this is NOT dataBinding.
 *
 * viewbinding allows you to skip all the findviewbyid calls and you should not have any null pointer issues
 * since the binding variable is used for everything.
 * note it must be turned on in the build.gradle file.
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, binding.etName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //to access the image view to a new bitmap for example.
        //binding.imageView.setImageBitmap(...);

    }
}