package edu.cs4730.intentDemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.cs4730.intentDemo.databinding.TwoActivityBinding;

/**
 * simple example.  it display the data sent to it and returns a fixed set of data.
 */

public class ActivityTwo extends AppCompatActivity {
    TwoActivityBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TwoActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main2, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String value1 = extras.getString("key1");
        String value2 = extras.getString("key2");
        if (value1 != null && value2 != null) {
            binding.EditText01.setText(value1);
            binding.EditText02.setText(value2);
        }
    }

    public void onClick(View view) {
        finish();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("returnKey1", "A2: some data");
        data.putExtra("returnKey2", "A2: more data");
        setResult(RESULT_OK, data);
        super.finish();
    }
}
