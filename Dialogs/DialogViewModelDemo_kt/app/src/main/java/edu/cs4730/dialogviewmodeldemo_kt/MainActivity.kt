package edu.cs4730.dialogviewmodeldemo_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationBarView
import edu.cs4730.dialogviewmodeldemo_kt.databinding.ActivityMainBinding


/**
 * very little to see here.  The listeners for the custom dialogs are implemented here
 * but otherwise the main work is in SupportDialogFragment and CustomFragment.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    private lateinit var myCustomFragment: CustomFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        val mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        fragmentManager = supportFragmentManager
        myCustomFragment = CustomFragment()

        binding.bnv.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item -> //At this point, we are doing the same thing that is done for menu selections.
            //if we had a onOptionsItemSelect method for a menu, we could just use it.
            val id = item.itemId
            if (id == R.id.nav_support) {
                fragmentManager.beginTransaction().replace(R.id.container, SupportDialogFragment())
                    .commit()
                item.isChecked = true
                return@OnItemSelectedListener true
            } else if (id == R.id.nav_custom) {
                fragmentManager.beginTransaction().replace(R.id.container, myCustomFragment)
                    .commit()
                item.isChecked = true
                return@OnItemSelectedListener true
            }
            false
        })
        if (savedInstanceState == null) {
            //add the first one as the default fragment.
            fragmentManager.beginTransaction().replace(R.id.container, SupportDialogFragment())
                .commit()
        }

        //now in DialogDemo there was a lot of call backs that are just solved by a ViewModel, making it nice an simple.
        mViewModel.getItem1LD.observe(
            this
        ) { s -> myCustomFragment.displaylog("Item1: $s") }
        mViewModel.getItem2LD.observe(
            this
        ) { s -> myCustomFragment.displaylog("Item1: $s") }
        mViewModel.getYesNoLD.observe(this) { b ->
            if (b) myCustomFragment.displaylog("Positive/Yes click!")
            else myCustomFragment.displaylog("Negative/No/Cancel click!")
        }
    }
}
