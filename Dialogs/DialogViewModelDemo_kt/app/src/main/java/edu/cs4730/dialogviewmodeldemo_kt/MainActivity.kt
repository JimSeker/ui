package edu.cs4730.dialogviewmodeldemo_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

/**
 * very little to see here.  The listeners for the custom dialogs are implemented here
 * but otherwise the main work is in SupportDialogFragment and CustomFragment.
 */
class MainActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var myCustomFragment: CustomFragment
    lateinit var bnv: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
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
                return@OnItemSelectedListener true;
            }
            false
        })
        if (savedInstanceState == null) {
            //add the first one as the default fragment.
            fragmentManager.beginTransaction().replace(R.id.container, SupportDialogFragment())
                .commit()
        }

        //now in DialogDemo there was a lot of call backs that are just solved by a ViewModel, making it nice an simple.
        mViewModel.getItem1LD.observe(this,
            Observer<String?> { s -> myCustomFragment.displaylog("Item1: $s") })
        mViewModel.getItem2LD.observe(this,
            Observer<String?> { s -> myCustomFragment.displaylog("Item1: $s") })
        mViewModel.getYesNoLD.observe(this,
            Observer<Boolean?> { b ->
                if (b)
                    myCustomFragment.displaylog("Positive/Yes click!")
                else
                    myCustomFragment.displaylog("Negative/No/Cancel click!")
            })
    }
}
