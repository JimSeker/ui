package edu.cs4730.modelviewrecyclerviewdemo;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/*
 * This is a simple demo of how to get data from an recyclerview demo all the way back to the
 * mainactivity.
 *
 * this is an example of using the ViewModel with LiveData instead of all the callBacks and listeners
 * to get data from the recyclerview to the mainactivity.  There are no callsbacks in this example.
 */

public class MainActivity extends AppCompatActivity  {
    String TAG = "MainActivity";

    DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewModel = ViewModelProviders.of(this).get( DataViewModel.class);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Name is " + mViewModel.getItem(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mViewModel.getItemLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(TAG, "triggered "  + s);
                Toast.makeText(getBaseContext(), "MainActivity Received " + s, Toast.LENGTH_LONG).show();
            }
        });

    }
}
