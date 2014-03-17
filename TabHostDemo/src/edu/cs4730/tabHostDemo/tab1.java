package edu.cs4730.tabHostDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class tab1 extends Activity {
	
	TabHostDemo parentclass = null;
	
	TextView log;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        log = (TextView)findViewById(R.id.textView1);
        
        //How to pass information between the tabs.
        //need this for both examples
        parentclass = (TabHostDemo) tab1.this.getParent();
        //first a simple "global variable"
          //parentclass.test is a string declared in the TabHostDemo.
        
        //The second method uses an intent.  In the onpause and onresume methods.
        //When you switch tabs, the current activity is paused and then the next activity is resumed.
        
        
    }
    @Override
    public void onPause() {
    	Log.d("tab1", "Paused");
    	parentclass.test = "Tab1";
    	
    	//Store some information the parents intent for the other activities..
        Intent i = getParent().getIntent();
        i.putExtra("tab", "This was Tab 1");
    	super.onPause();
    	
    }
    @Override
    public void onResume() {
    	Log.d("tab1", "Resume");
    	  if (parentclass.test != null)  //should not be null, but just in case.
    	    log.setText(parentclass.test);
    	  
    	  //Retrieve data from the parents intent, this data could be from any of the three tabs.
          Intent i = getParent().getIntent();
          log.append("\nI is "+i.getStringExtra("tab"));
    	super.onResume();
    }
}
