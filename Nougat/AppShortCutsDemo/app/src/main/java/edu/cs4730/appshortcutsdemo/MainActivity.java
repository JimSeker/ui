package edu.cs4730.appshortcutsdemo;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * this is a simple demo to show how static shorts (see xml/shortcuts.xml)
 *
 * this code will add/refresh two dynamic short cuts as well.
 * 1 to a website
 * 2 to the commonActivity.
 *
 * once this example makes since, then you will be able to understand google's very complex example
 * of appshortcuts https://github.com/googlesamples/android-AppShortcuts/
 *
 */

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    private ShortcutManager mShortcutManager;

    TextView logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger = (TextView) findViewById(R.id.logger);

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
            for (ShortcutInfo shortcut : mShortcutManager.getDynamicShortcuts()) {
                String temp = "ID: " + shortcut.getId() + " ShortLabel:" + shortcut.getShortLabel();
                Log.v(TAG, temp);
                logthis(temp);
            }

            //we should refresh them, in case things changed in dynamic.  (or in debugging, if we changed something.  doesn't auto update
            addshortcuts();


        }

        if (getIntent().getAction().compareTo("edu.cs4730.appshortcutsdemo.CustomAction") ==0) {
            //this is our custom intent fromt he xml file
            logthis("Custom Action intent from the Static shortcut.");
        }


    }

    void addshortcuts() {
        //first create the list.
        ArrayList<ShortcutInfo> myList = new ArrayList<ShortcutInfo>();
        ShortcutInfo item;
        //now add them.
        //first one, a web link to cosc 4735 pages
        item = new ShortcutInfo.Builder(this,"id1")
                .setShortLabel("Web Site")
                .setLongLabel("Cosc 4735 Web site")
                .setIcon(Icon.createWithResource(this, R.drawable.bookmark))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.cs.uwyo.edu/~seker/courses/4735/")))
                .build();
        myList.add(item);
        //second one, a custom intent to commonActivity.
        item = new ShortcutInfo.Builder(this,"id2")
                .setShortLabel("Common Act message")
                .setLongLabel("Start a message on Commmon Activity")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_touch_app_black_24dp))
                .setIntent(
                        new Intent("edu.cs4730.appshortcutsdemo.AddMessage")
                        .setClassName("edu.cs4730.appshortcutsdemo","edu.cs4730.appshortcutsdemo.CommonActivity")
                        //this is like any intent, you can add data to it as well, so key pairs of data.
                        .putExtra("Name", "fred")
                )
                .build();
        myList.add(item);

        //add the list to the shortcutmanager.
        mShortcutManager.setDynamicShortcuts(myList);
        //or we could have added each individually with this statement:  (addd skip myList)
        //mShortcutManager.addDynamicShortcuts(Arrays.asList(item));


    }

    /*
     * simple helper function to log data to the screen
     */

    public void logthis(String item) {
        logger.append(item);
        logger.append("\n");
    }
}
