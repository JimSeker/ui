package edu.cs4730.tabHostDemo;


/*
 * This code uses a depreciated TabActivity.  It will not be updated and is being left at android v2.3.3
 * 
 * This example is based on the the Android example http://developer.android.com/resources/tutorials/views/hello-tabwidget.html
 * With fixes from stackoverflow here: http://stackoverflow.com/questions/2209406/issues-with-android-tabhost-example
 * 
 * The information passing comes from the following two places, with fixes that I(JW) made to get them to work correctly
 * http://stackoverflow.com/questions/4523181/android-tab-pass-intent-extras-to-new-tabs-activity
 * http://stackoverflow.com/questions/2979778/passing-arrayliststring-between-tabs
 */

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class TabHostDemo extends TabActivity {
	public String test = "before oncreate!";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        test = "OnCreate";
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, tab1.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("artists").setIndicator("Tab1")//,
                          //res.getDrawable(R.drawable.ic_tab1))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, tab2.class);
        spec = tabHost.newTabSpec("albums").setIndicator("Albums") //,
                          //res.getDrawable(R.drawable.ic_tab2))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, tab3.class);
        spec = tabHost.newTabSpec("songs").setIndicator("Songs",
                          res.getDrawable(R.drawable.ic_tab3))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);  //first tab.

    }
    @Override
    public void onPause() {
    	Log.d("TabHostDemo", "Paused");
    	test = "Act Paused";


    	super.onPause();
    	
    }
    @Override
    public void onResume() {
    	Log.d("TabHostDemo", "Resume");
    	super.onResume();
    }
}