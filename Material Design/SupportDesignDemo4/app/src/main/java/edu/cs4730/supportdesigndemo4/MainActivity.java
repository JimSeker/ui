package edu.cs4730.supportdesigndemo4;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.List;

/**
 * an attempt at a collapsing toolbar (will, I thought it would scroll off as well).  show title text
 * is an issue.
 * <p>
 * based on http://android-developers.blogspot.com/2015/05/android-design-support-library.html  so
 * based on android docs in the past... it's an error in their code... or as it turns out a note, that
 * well isn't clear at all.
 * <p>
 * basing come from the talltoolbar example to get icons and stuff.
 */

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    myAdapter mAdapter;
    private Toolbar toolbar;
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerlayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //use the toolbar instead of the default one.
        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        //SO to get any text or title it is set here.  otherwise, there is no text in the toolbar.
        mCollapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayout1);
        mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));


        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        List<String> values = Arrays.asList("Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2");

        //setup the RecyclerView
        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new myAdapter(values, R.layout.my_row, this);
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        //standard navigation drawer setup.
        mDrawerlayout = findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this,  // host activity
            mDrawerlayout,  //drawerlayout object
            toolbar,  //toolbar
            R.string.drawer_open,  //open drawer description  required!
            R.string.drawer_close) {  //closed drawer description

            //called once the drawer has closed.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Categories");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            //called when the drawer is now open.
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //likely don't need this.
                getSupportActionBar().setTitle(R.string.app_name);
                //need this or no title shows.
                mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        //To disable the icon for the drawer, change this to false
        //mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerlayout.addDrawerListener(mDrawerToggle);
        //this ia the support Navigation view.
        mNavigationView = findViewById(R.id.navview);
        //setup a listener, which acts very similiar to how menus are handled.
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //instead of this below, we could just call the
                //onOptionsItemSelected(menuItem);

                int id = menuItem.getItemId();
                //noinspection SimplifiableIfStatement
                if (id == R.id.navigation_item_1) {
                    //do something.
                } else if (id == R.id.navigation_item_2) {
                    //do something
                } else if (id == R.id.navigation_item_3) {
                    //do something.
                } else if (id == R.id.navigation_item_4) {
                    //do something.
                }
                //now mark it the menu as checked (highlighted in ui) just close the drawer.
                menuItem.setChecked(true);
                mDrawerlayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
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
