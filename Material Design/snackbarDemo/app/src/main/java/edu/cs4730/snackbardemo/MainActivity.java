package edu.cs4730.snackbardemo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

/**
 * A demo of the snackbar callbacks.  So you can tell if the user swiped it away, or other actions.
 * And yes, the irony of using a toast to show snackbar is not lost on me either.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", SBonClickListener)
                    .addCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            super.onDismissed(snackbar, event);
                            switch (event) {
                                case  Snackbar.Callback.DISMISS_EVENT_ACTION:
                                    Toast.makeText(getBaseContext(), "Dismiss from onclick", Toast.LENGTH_SHORT).show();
                                    break;
                                case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                                    Toast.makeText(getBaseContext(), "Dismiss from user swipe", Toast.LENGTH_SHORT).show();
                                    break;
                                case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                                    Toast.makeText(getBaseContext(), "Dismiss from timeout", Toast.LENGTH_SHORT).show();
                                    break;
                                case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                                    Toast.makeText(getBaseContext(), "Dismiss from another snackbar showing", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(getBaseContext(), "Dismiss for other reason.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    })
                    .show();
            }
        });
    }

    View.OnClickListener SBonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getBaseContext(), "You clicked Action", Toast.LENGTH_SHORT).show();
        }
    };
}
