package edu.cs4730.appshortcutsdemo;

import android.content.pm.ShortcutManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


/*
 * this is a simple demo to show how static shorts (see xml/shortcuts.xml
 */

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    private ShortcutManager mShortcutManager;

    TextView logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get an instance of the shortcurManager
        mShortcutManager = getSystemService(ShortcutManager.class);  //ShortcutManager.class is android, not a class I created.

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Intent action is " + getIntent().getAction());
        if (mShortcutManager.getDynamicShortcuts().size() == 0) {
            //there are no dynamic shortcuts, so lets add two of them.
            Log.v(TAG, "no shortcuts, adding 2");
            logthis("no shortcuts, adding 2");
            addshortcuts();
        } else {
            //log the shortcuts to the screen.
        }

        if (getIntent().getAction().compareTo("edu.cs4730.appshortcutsdemo.CustomAction") ==0) {
            //this is our custom intent fromt he xml file
            logthis("Custom Action intent from the Static shortcut.");
        }


    }

    void addshortcuts() {
        //first create the list.

        //now add them.

    }

    /*
     * simple helper function to log data to the screen
     */

    public void logthis(String item) {
        logger.append(item);
        logger.append("\n");
    }
}
