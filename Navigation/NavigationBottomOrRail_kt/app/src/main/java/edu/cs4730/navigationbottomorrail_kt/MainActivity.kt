package edu.cs4730.navigationbottomorrail_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.badge.BadgeDrawable
import edu.cs4730.navigationbottomorrail_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }


        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        //Note for this to work with arch Navigation, these id must be the same id in menu.xml and the nav_graph.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.action_first, R.id.action_second, R.id.action_third
        )
            .build()


        //set the controller, note both layouts use the name for nav_host_fragment, so this works for both.
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        val badge: BadgeDrawable


        //if you don't want an action bar, then comment this out.
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        //but we can't use the same navigationUI.setupWithNavController() for both the bottom navigation view and the navigation rail.  idk why, I tried and it died.
        //so we need to check which one is being used.  Each has a different id in the layout file.
        if (binding.navView != null) {
            // this is a bottom navigation view, so we need to set it up.
            NavigationUI.setupWithNavController(binding.navView!!, navController)
            badge = binding.navView!!.getOrCreateBadge(R.id.action_second)
        } else {
            // this is a navigation rail, so we need to set it up.
            NavigationUI.setupWithNavController(binding.navRail!!, navController)
            badge = binding.navRail!!.getOrCreateBadge(R.id.action_second)
        }
        badge.number = 12 //should show a 12 in the "badge" for the second one.
        badge.isVisible = true

    }
}