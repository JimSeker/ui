package edu.cs4730.quicksettingstiledemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;


public class QuickSettingsTapService extends TileService {

    String TAG = "MyTileService";


    /*
     *  called when the tile is first added to the tile screen.
     */
    @Override
    public void onTileAdded() {

        Log.d(TAG, "onTileAdded");
    }

    /*
    * the tile is now visible  update it.  like add listeners.
     */
    @Override
    public void onStartListening() {
        Log.d(TAG, "onStartListening");
        boolean working = getServiceStatus();
        Tile tile = getQsTile();

        if (working) {
            //turn on the tile here.
            tile.setIcon(Icon.createWithResource(this, R.drawable.cooltile_on));
            tile.setLabel("Cool tile");
            tile.setContentDescription("cool tile is On");
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            //turn on the tile here.
            tile.setIcon(Icon.createWithResource(this, R.drawable.cooltile_off));
            tile.setLabel("Cool tile");
            tile.setContentDescription("cool tile is Off");
            tile.setState(Tile.STATE_INACTIVE);
        }
        tile.updateTile();

    }

    /*
    * tile is no longer visible, stop updating it.
     */
   @Override
   public void onStopListening () {
       Log.d(TAG, "onStopListening");
   }

/*
* the tile has been removed form the quick screen.
 */
    @Override
    public void onTileRemoved() {
        Log.d(TAG, "onTileRemoved");
    }

    /*
    *  when the user clicks the tile.
     */
    @Override
    public void onClick() {
        Log.d(TAG, "onClick");
        boolean working = getServiceStatus();

        Tile tile = getQsTile();
        if (working) {
            //turn on the tile here.
            tile.setIcon(Icon.createWithResource(this, R.drawable.cooltile_on));
            tile.setLabel("Cool tile");
            tile.setContentDescription("cool tile is On");
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            //turn on the tile here.
            tile.setIcon(Icon.createWithResource(this, R.drawable.cooltile_off));
            tile.setLabel("Cool tile");
            tile.setContentDescription("cool tile is Off");
            tile.setState(Tile.STATE_INACTIVE);
        }
        tile.updateTile();
    }

    private static final String SERVICE_STATUS_FLAG = "serviceStatus";
    private static final String PREFERENCES_KEY = "edu.cs4735.android_quick_settings";

    // Access storage to see how many times the tile
    // has been tapped.
    private boolean getServiceStatus() {

        SharedPreferences prefs =
                getApplicationContext()
                        .getSharedPreferences(PREFERENCES_KEY,
                                MODE_PRIVATE);

        boolean isActive = prefs.getBoolean(SERVICE_STATUS_FLAG, false);
        isActive = !isActive;

        prefs.edit().putBoolean(SERVICE_STATUS_FLAG, isActive).apply();

        return isActive;
    }

}
