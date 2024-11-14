package edu.cs4730.supportdesignbottomnavdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.MenuItem;

import edu.cs4730.supportdesignbottomnavdemo.databinding.ActivityMainBinding;

/**
 * This is a simple example of how to use a the BottomNavigationView, which was
 * introduced in support design v25.0.0.   The navigation changes the fragment, using the
 * onItemSelected listener.  Note the list for the navigation view is a menu xml list, found
 * in the menu resource.
 */
public class MainActivity extends AppCompatActivity {

    BlankFragment recentFrag, favFrag, nearbyFrag;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.activityMain, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        //setup the three fragments, which is just the same blankfragment with a different text, that will be used by the nav.
        recentFrag = BlankFragment.newInstance("Recent", "");
        favFrag = BlankFragment.newInstance("Favorites", "");
        nearbyFrag = BlankFragment.newInstance("Nearby", "");

        binding.bnv.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //At this point, we are doing the same thing that is done for menu selections.
                //if we had a onOptionsItemSelect method for a menu, we could just use it.
                int id = item.getItemId();
                if (id == R.id.recent) {
                    getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), recentFrag).commit();
                    return true;
                } else if (id == R.id.favorites) {
                    getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), favFrag).commit();
                    return true;
                } else if (id == R.id.nearby) {
                    getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), nearbyFrag).commit();
                    return true;
                }
                return false;
            }
        });

        //set the first one as the default.
        getSupportFragmentManager().beginTransaction().add(binding.container.getId(), recentFrag).commit();
    }
}
