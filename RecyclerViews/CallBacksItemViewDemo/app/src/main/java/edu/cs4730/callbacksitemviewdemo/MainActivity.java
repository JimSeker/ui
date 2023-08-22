package edu.cs4730.callbacksitemviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import edu.cs4730.callbacksitemviewdemo.databinding.ActivityMainBinding;

/**
 * This is a simple demo of how to get data from an recyclerview demo all the way back to the
 * mainactivity without using any buttons.  The user can click on the item in the recyclerview and
 * it comes all the way back to mainactivity with info.
 * <p>
 * Note it's itemview in the adapter that is clickable.  this is the callback.
 */


public class MainActivity extends AppCompatActivity implements MainFragment.OntransInteractionCallback {
    String TAG = "MainActivity";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
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
