package edu.cs4730.appshortcutsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;

import edu.cs4730.appshortcutsdemo.databinding.ActivityCommonBinding;

/**
 * this activity has no launcher, so it is launched via the shortcuts (or via the MainActivity)
 * <p>
 * it doesn't really do anything, it will deal with a custom intent and dig out a data item as well.
 */

public class CommonActivity extends AppCompatActivity {

    ActivityCommonBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.activityCommon, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        if (getIntent().getAction().compareTo("edu.cs4730.appshortcutsdemo.AddMessage") == 0) {
            //custom intent, not view or main
            Bundle extra = getIntent().getExtras();
            if (extra != null)
                binding.textView2.setText("\n Add message " + extra.getString("Name"));
        }
    }
}
