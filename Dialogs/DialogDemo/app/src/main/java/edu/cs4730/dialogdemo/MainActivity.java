package edu.cs4730.dialogdemo;

import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationBarView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import edu.cs4730.dialogdemo.databinding.ActivityMainBinding;


/**
 * very little to see here.  The listeners for the custom dialogs are implemented here
 * but otherwise the main work is in SupportDialogFragment and CustomFragment.
 */

public class MainActivity extends AppCompatActivity
    implements myEditNameDialogFrag.EditNameDialogListener,
    myDialogFragment.OnDialogFragmentListener,
    myAlertDialogFragment.OnDialogFragmentListener,
    MultiInputDialogFragment.OnDialogFragmentInteractionListener {

    ActivityMainBinding binding;
    FragmentManager fragmentManager;
    CustomFragment myCustomFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        fragmentManager = getSupportFragmentManager();
        myCustomFragment = new CustomFragment();

        binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //At this point, we are doing the same thing that is done for menu selections.
                //if we had a onOptionsItemSelect method for a menu, we could just use it.
                int id = item.getItemId();
                if (id == R.id.nav_support) {
                    fragmentManager.beginTransaction().replace(binding.container.getId(), new SupportDialogFragment()).commit();
                    item.setChecked(true);
                    return true;
                } else if (id == R.id.nav_custom) {
                    fragmentManager.beginTransaction().replace(binding.container.getId(), myCustomFragment).commit();
                    item.setChecked(true);
                }
                return false;
            }
        });

        if (savedInstanceState == null) {
            //add the first one as the default fragment.
            fragmentManager.beginTransaction().replace(binding.container.getId(), new SupportDialogFragment()).commit();
        }


    }


    /**
     * These three methods are the callback methods for the dialog fragment callbacks.
     * note doPositiveClick and doNegativeClick are for both AlertDialogFrag1, while doItem
     * is only for the myDialogFragment listener.
     */
    //for Both myDialogFragment and myAlterDialogFragment
    public void doPositiveClick() {
        // Do stuff here.
        myCustomFragment.displaylog("Positive/Yes click!");

    }

    //for Both myDialogFragment and myAlterDialogFragment
    public void doNegativeClick() {
        // Do stuff here.
        myCustomFragment.displaylog("Negative/No/Cancel click!");
    }

    //for myDialogFragment
    public void doItem(String item) {
        myCustomFragment.displaylog(item);
    }

    //for the myEditNameDialogFrag
    public void onFinishEditDialog(String inputText) {
        myCustomFragment.displaylog("Hi, " + inputText);
    }

    //for the MultiInputDialgoFragment
    @Override
    public void onMultiInputInteraction(String[] items) {
        myCustomFragment.displaylog("Item 0: " + items[0]);
        myCustomFragment.displaylog("Item 1: " + items[1]);
    }
}
