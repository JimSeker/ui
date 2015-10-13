package edu.cs4730.snackbardemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                                switch (event) {
                                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                                        Toast.makeText(getBaseContext(), "Dimiss from onclick",Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                                        Toast.makeText(getBaseContext(), "Dimiss from user swipe",Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                                        Toast.makeText(getBaseContext(), "Dimiss from timeout",Toast.LENGTH_SHORT).show();
                                        break;
                                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                                        Toast.makeText(getBaseContext(), "Dimiss from another snackbar showing",Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
    }

    View.OnClickListener SBonClickListener = new View.OnClickListener(){
        public void  onClick  (View  v){
            Toast.makeText(getBaseContext(),"You clicked Action", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
