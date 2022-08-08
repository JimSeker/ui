package edu.cs4730.appshortcutsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * this activity has no launcher, so it is launched via the shortcuts (or via the MainActivity)
 *
 * it doesn't really do anything, it will deal with a custom intent and dig out a data item as well.
 */

public class CommonActivity extends AppCompatActivity {

    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        log = (TextView) findViewById(R.id.textView2);

        if (getIntent().getAction().compareTo("edu.cs4730.appshortcutsdemo.AddMessage") == 0) {
            //custom intent, not view or main
            Bundle extra = getIntent().getExtras();
            if (extra != null)
                log.setText("\n Add message " + extra.getString("Name"));
        }
    }
}
