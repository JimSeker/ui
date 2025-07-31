package edu.cs4730.bottomnavigationviewdemo_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import edu.cs4730.bottomnavigationviewdemo_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        /* if not using arch navigation, then you need to implement this.
        binding.navView.setOnItemSelectedListener(
            NavigationBarView.OnItemSelectedListener { item -> //setup the fragments here.
                val id = item.itemId
                if (id == R.id.action_first) {
                    supportFragmentManager.beginTransaction().replace(binding.container.id, OneFragment())
                        .commit()
                    item.isChecked = true
                    return@OnItemSelectedListener true
                } else if (id == R.id.action_second) {
                    supportFragmentManager.beginTransaction().replace(binding.container.id, TwoFragment())
                        .commit()
                    item.isChecked = true
                } else if (id == R.id.action_third) {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.container.id, threeFragment()).commit()
                    item.isChecked = true
                }
                false
            }
        )*/


        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        //Note for this to work with arch Navigation, these id must be the same id in menu.xml and the nav_graph.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.action_first, R.id.action_second, R.id.action_third
        )
            .build()
        //val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)

        //In order to have badges, you need to use the Theme.MaterialComponents.DayNight  (doesn't have to be daynight, but MaterialComponents).
        val badge = binding.navView.getOrCreateBadge(R.id.action_second)
        badge.number = 12 //should show a 12 in the "badge" for the second one.

        badge.isVisible = true
    }
}