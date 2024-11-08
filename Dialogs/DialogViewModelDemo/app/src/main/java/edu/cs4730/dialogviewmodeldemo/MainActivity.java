package edu.cs4730.dialogviewmodeldemo;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


/**
 * very little to see here.  The listeners for the custom dialogs are implemented here
 * but otherwise the main work is in SupportDialogFragment and CustomFragment.
 */

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    CustomFragment myCustomFragment;
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        DataViewModel mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        fragmentManager = getSupportFragmentManager();
        myCustomFragment = new CustomFragment();

        bnv = findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //At this point, we are doing the same thing that is done for menu selections.
                //if we had a onOptionsItemSelect method for a menu, we could just use it.
                int id = item.getItemId();
                if (id == R.id.nav_support) {
                    fragmentManager.beginTransaction().replace(R.id.container,  new SupportDialogFragment()).commit();
                    item.setChecked(true);
                    return true;
                } else if (id == R.id.nav_custom) {
                    fragmentManager.beginTransaction().replace(R.id.container, myCustomFragment).commit();
                    item.setChecked(true);
                    return true;
                }
                return false;
            }
        });

        if (savedInstanceState == null) {
            //add the first one as the default fragment.
            fragmentManager.beginTransaction().replace(R.id.container,  new SupportDialogFragment()).commit();
        }

        //now in DialogDemo there was a lot of call backs that are just solved by a ViewModel, making it nice an simple.


        mViewModel.getItem1LD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                myCustomFragment.displaylog("Item1: " + s);
            }
        });
        mViewModel.getItem2LD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                myCustomFragment.displaylog("Item1: " + s);
            }
        });
        mViewModel.getYesNoLD().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean b) {
                if (b)
                    myCustomFragment.displaylog("Positive/Yes click!");
                else
                    myCustomFragment.displaylog("Negative/No/Cancel click!");
            }
        });
    }

}
