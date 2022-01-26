package edu.cs4730.dialogdemo_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

/**
 * very little to see here.  The listeners for the custom dialogs are implemented here
 * but otherwise the main work is in SupportDialogFragment and CustomFragment.
 */
class MainActivity : AppCompatActivity(), myEditNameDialogFrag.EditNameDialogListener,
    myDialogFragment.OnDialogFragmentListener, myAlertDialogFragment.OnDialogFragmentListener,
    MultiInputDialogFragment.OnDialogFragmentInteractionListener {

    lateinit var fragmentManager: FragmentManager
    lateinit var myCustomFragment: CustomFragment
    lateinit var bnv: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager
        myCustomFragment = CustomFragment()
        bnv = findViewById(R.id.bnv)
        bnv.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item -> //At this point, we are doing the same thing that is done for menu selections.
            //if we had a onOptionsItemSelect method for a menu, we could just use it.
            val id = item.itemId
            if (id == R.id.nav_support) {
                fragmentManager.beginTransaction()
                    .replace(R.id.container, SupportDialogFragment()).commit()
                item.isChecked = true
                return@OnItemSelectedListener true
            } else if (id == R.id.nav_custom) {

                fragmentManager.beginTransaction().replace(R.id.container, myCustomFragment)
                    .commit()
                item.isChecked = true
            }
            false
        })
        if (savedInstanceState == null) {
            //add the first one as the default fragment.
            fragmentManager.beginTransaction().replace(R.id.container, SupportDialogFragment())
                .commit()
        }
    }

    /**
     * These three methods are the callback methods for the dialog fragment callbacks.
     * note doPositiveClick and doNegativeClick are for both AlertDialogFrag1, while doItem
     * is only for the myDialogFragment listener.
     */
    //for Both myDialogFragment and myAlterDialogFragment
    override fun doPositiveClick() {
        // Do stuff here.
        myCustomFragment.displaylog("Positive/Yes click!")
    }

    //for Both myDialogFragment and myAlterDialogFragment
    override fun doNegativeClick() {
        // Do stuff here.
        myCustomFragment.displaylog("Negative/No/Cancel click!")
    }

    //for myDialogFragment
    override fun doItem(item: String) {
        myCustomFragment.displaylog(item)
    }


    //for the MultiInputDialgoFragment
    override fun onMultiInputInteraction(items: Array<String>) {
        myCustomFragment.displaylog("Item 0: " + items[0])
        myCustomFragment.displaylog("Item 1: " + items[1])
    }

    override fun onFinishEditDialog(inputText: String) {
        myCustomFragment.displaylog("Hi, $inputText")
    }
}
