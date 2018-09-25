package edu.cs4730.snackbardemo;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/*
 * A demo of the snackbar callbacks.  So you can tell if the user swiped it away, or other actions.
 * And yes, the irony of using a toast to show snackbar is not lost on me either.
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
                                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                                        Toast.makeText(getBaseContext(), "Dimiss from onclick", Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                                        Toast.makeText(getBaseContext(), "Dimiss from user swipe", Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                                        Toast.makeText(getBaseContext(), "Dimiss from timeout", Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                                        Toast.makeText(getBaseContext(), "Dimiss from another snackbar showing", Toast.LENGTH_SHORT).show();
                                        break;
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
