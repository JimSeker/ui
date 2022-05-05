package edu.cs4730.archnavdrawerlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerlayout;
    private NavigationView mNavigationView;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //standard navigation drawer setup.
        mDrawerlayout = findViewById(R.id.drawer_layout);

        //this is needed if you want the open/close to show in the toolbar.
        mDrawerToggle = new ActionBarDrawerToggle(this,  // host activity
            mDrawerlayout,  //drawerlayout object
            toolbar,  //toolbar
            R.string.drawer_open,  //open drawer description  required!
            R.string.drawer_close) {  //closed drawer description

            //called once the drawer has closed.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("Pick one");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            //called when the drawer is now open.
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerlayout.addDrawerListener(mDrawerToggle);

        //this ia the support Navigation view.
        mNavigationView = findViewById(R.id.navview);
        //setup a listener, which acts very similar to how menus are handled, but with a NavView and arch Navigation
        //this how to do it manually.
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                //we could just as easily call onOptionsItemSelected(menuItem) and how it deal with it.
//                int id = menuItem.getItemId();
//                if (id == R.id.action_first) {
//                    //load fragment
//                    if (!menuItem.isChecked()) {  //only need to do this if fragment is already loaded.
//                        menuItem.setChecked(true);  //make sure to check/highlight the item.
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new OneFragment()).commit();
//                    }
//                    mDrawerlayout.closeDrawers();  //close the drawer, since the user has selected it.
//                    return true;
//                } else if (id == R.id.action_second) {
//                    //load fragment
//                    if (!menuItem.isChecked()) {  //only need to do this if fragment is already loaded.
//                        menuItem.setChecked(true); //make sure the item is checked/highlighted
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TwoFragment()).commit();
//                    }
//                    //now close the nav drawer.
//                    mDrawerlayout.closeDrawers();
//                    return true;
//                } else if (id == R.id.action_third) {
//                    //load fragment
//                    if (!menuItem.isChecked()) {  //only need to do this if fragment is already loaded.
//                        menuItem.setChecked(true);
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new threeFragment()).commit();
//                    }
//                    mDrawerlayout.closeDrawers();
//                    return true;
//                }
//                return false;
//            }
//        });
        //Note for this to work with arch Navigation, these id must be the same id in menu.xml and the nav_graph.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
            R.id.action_first, R.id.action_second, R.id.action_third)
            .build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(mNavigationView, navController);
    }


    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}