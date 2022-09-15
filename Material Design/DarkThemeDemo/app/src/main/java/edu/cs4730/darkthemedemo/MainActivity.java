package edu.cs4730.darkthemedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

/**
 * This example is how to change themes dynamically and use to use the DayNight theme in
 * appcompat and material design.
 *
 * The code here just changes the themes.  The default is the light theme.   See the styles.xml
 * file in /values for the 6 themes. set a theme of the each, but left the "daynight" as the default color scheme.
 *
 * A note the Theme can only be set before the super.onCreate(), so preferences are used so when the
 * app start it can set the theme.
 *
 * Need add the DayNight (both app and material?) stuff for AppCompatDelegate.setDefaultNightMode()
 *  with MODE_LIGHT_NO/YES and MODE_NIGHT_AUTO_BATTERY  System default - MODE_NIGHT_FOLLOW_SYSTEM
 *
 *  don't change the commit() to apply() or the app will crash a lot.  need to finish the commit before
 *  the app restarts the drawing.
 */

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    int mytheme;
    @SuppressLint("ApplySharedPref")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("example", MODE_PRIVATE);
        mytheme = preferences.getInt("Theme", R.style.AppTheme);
        setTheme(mytheme);  //must be fore the super!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("Theme",R.style.AppTheme);
                editor.commit();
                recreate();
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("Theme",R.style.AppTheme1);
                editor.commit();
                recreate();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("Theme",R.style.AppTheme2);
                editor.commit();
                recreate();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("Theme",R.style.AppTheme3);
                editor.commit();
                recreate();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("Theme",R.style.AppTheme4);
                editor.commit();
                recreate();
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("Theme",R.style.AppTheme5);
                editor.commit();
                recreate();
            }
        });

    }
}
