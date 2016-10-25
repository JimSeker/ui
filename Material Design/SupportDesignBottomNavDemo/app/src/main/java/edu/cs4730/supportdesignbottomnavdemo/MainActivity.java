package edu.cs4730.supportdesignbottomnavdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

/*
 * This is a simple example of how to use a the BottomNavigationView, which was
 * introduced in support design v25.0.0.   The navigation changes the fragment, using the
 * onNavigationItemSelected listener.  Note the list for the naviagion view is a menu xml list, found
 * in the menu resource.
 */
public class MainActivity extends AppCompatActivity {

    BlankFragment recentFrag, favFrag, nearbyFrag;
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup the three fragments, that will be used by the nav.
        recentFrag = BlankFragment.newInstance("Recent", "");
        favFrag = BlankFragment.newInstance("Favorites", "");
        nearbyFrag = BlankFragment.newInstance("Nearby", "");

        bnv = (BottomNavigationView) findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //At this point, we are doing the same thing that is done for menu selections.
                //if we had a onOptionsItemSelect method for a menu, we could just use it.
                int id = item.getItemId();
                if (id == R.id.recent) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, recentFrag).commit();
                    return true;
                } else if (id == R.id.favorites) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, favFrag).commit();
                    return true;
                } else if (id == R.id.nearby) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, nearbyFrag).commit();
                    return true;
                }
                return false;
            }
        });

        //set the first one as the default.
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, recentFrag).commit();
    }
}
