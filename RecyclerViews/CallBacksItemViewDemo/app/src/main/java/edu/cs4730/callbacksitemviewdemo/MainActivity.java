package edu.cs4730.callbacksitemviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * This is a simple demo of how to get data from an recyclerview demo all the way back to the
 * mainactivity without using any buttons.  The user can click on the item in the recyclerview and
 * it comes all the way back to mainactivity with info.
 *
 * Note it's itemview in the adapter that is clickable.  this is the callback.
 *
 */


public class MainActivity extends AppCompatActivity implements MainFragment.OntransInteractionCallback  {
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void ontransInteraction(String item) {
        Log.v(TAG, "Callback at " + TAG);
        Toast.makeText(this, "MainActivity Received " + item, Toast.LENGTH_LONG).show();
    }
}
