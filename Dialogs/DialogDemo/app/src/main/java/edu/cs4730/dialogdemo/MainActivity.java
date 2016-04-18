package edu.cs4730.dialogdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


/*
 * very little to see here.  The listeners for the custom dialogs are implemented here
 * but otherwise the main work is in SupportDialogFragment and CustomFragment.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        myEditNameDialogFrag.EditNameDialogListener,
        myDialogFragment.OnDialogFragmentListener, myAlertDialogFragment.OnDialogFragmentListener{

    FragmentManager fragmentManager;
    CustomFragment myCustomFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //add the first one as the default fragment.
        fragmentManager.beginTransaction().replace(R.id.container, new SupportDialogFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_support) {
            fragmentManager.beginTransaction().replace(R.id.container, new SupportDialogFragment()).commit();
        } else if (id == R.id.nav_custom) {
            if (myCustomFragment == null)
                 myCustomFragment = new CustomFragment();
            fragmentManager.beginTransaction().replace(R.id.container, myCustomFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
*    These three methods are the callback methods for the dialog fragment callbacks.
*    note doPositiveClick and doNegativeClick are for both AlertDialogFrag1, while doItem
*    is only for the myDialogFragment listener.
*/
    //for Both myDialogFragment and myAlterDialogFragment
    public void doPositiveClick() {
        // Do stuff here.
        myCustomFragment.displaylog("Positive/Yes click!");

    }
    //for Both myDialogFragment and myAlterDialogFragment
    public void doNegativeClick() {
        // Do stuff here.
        myCustomFragment.displaylog("Negative/No/Cancel click!");

    }
    //for myDialogFragment
    public void doItem(String item) {
        myCustomFragment.displaylog(item);
    }

    //for the myEditNameDialogFrag
    public void onFinishEditDialog(String inputText) {
        myCustomFragment.displaylog("Hi, " + inputText);
    }
}
