package edu.cs4730.menudemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NavUtils;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import edu.cs4730.menudemo.databinding.ActionmenuBinding;

/**
 * This activity show how to setup both buttons, and menus (and sub menus).
 * but all the work is in the menu/action.xml file.
 */

public class ActionMenuActivity extends AppCompatActivity {
    ActionmenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActionmenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //turn on up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate "up" the demo structure to the launchpad activity.
            // See http://developer.android.com/design/patterns/navigation.html for more.
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
            return true;
        }
        //Normally you would deal with all the menu items here, which I'm ignoring for the purpose of the example.
        return super.onOptionsItemSelected(item);
    }

}
