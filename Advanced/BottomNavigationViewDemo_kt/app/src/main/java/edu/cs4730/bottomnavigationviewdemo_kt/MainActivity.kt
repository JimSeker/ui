package edu.cs4730.bottomnavigationviewdemo_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        /* if not using arch navigation, then you need to implement this.
        navView.setOnItemSelectedListener(
            NavigationBarView.OnItemSelectedListener { item -> //setup the fragments here.
                val id = item.itemId
                if (id == R.id.action_first) {
                    supportFragmentManager.beginTransaction().replace(R.id.container, OneFragment())
                        .commit()
                    item.isChecked = true
                    return@OnItemSelectedListener true
                } else if (id == R.id.action_second) {
                    supportFragmentManager.beginTransaction().replace(R.id.container, TwoFragment())
                        .commit()
                    item.isChecked = true
                } else if (id == R.id.action_third) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, threeFragment()).commit()
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
        NavigationUI.setupWithNavController(navView, navController)

        //In order to have badges, you need to use the Theme.MaterialComponents.DayNight  (doesn't have to be daynight, but MaterialComponents).

        //In order to have badges, you need to use the Theme.MaterialComponents.DayNight  (doesn't have to be daynight, but MaterialComponents).
        val badge = navView.getOrCreateBadge(R.id.action_second)
        badge.number = 12 //should show a 12 in the "badge" for the second one.

        badge.isVisible = true
    }
}