package edu.cs4730.supportdesigndemo3_kt

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


/**
 * example mostly shows a collapsing toolbar, which is in the xml
 * it also changes the status and toolbar color as well, plus the text size as the toolbar is collapsing.
 *
 * Some code from http://www.tutorialsbuzz.com/2015/11/android-collapsingtoolbarlayout-example_7.html
 * and http://antonioleiva.com/collapsing-toolbar-layout/
 */
class MainActivity : AppCompatActivity() {
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        collapsingToolbarLayout =
            findViewById<View>(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        collapsingToolbarLayout.title = resources.getString(R.string.app_name)
        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        dynamicToolbarColor()
        toolbarTextAppearance()
    }

    private fun dynamicToolbarColor() {
        val bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.wolf_gray
        )
        Palette.from(bitmap).generate { palette -> //a dark color  a dark blue
            collapsingToolbarLayout.setContentScrimColor(
                palette!!.getDarkVibrantColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.colorPrimary
                    )
                )
            )
            //a muted color   mostly gray
//            collapsingToolbarLayout.setContentScrimColor(
//                palette.getMutedColor(
//                    ContextCompat.getColor(
//                        applicationContext, R.color.colorPrimary
//                    )
//                )
//            )
            //dark muted color  very dark blue, almost black.
//            collapsingToolbarLayout.setContentScrimColor(
//                palette.getDarkMutedColor(
//                    ContextCompat.getColor(
//                        applicationContext, R.color.colorPrimary
//                    )
//                )
//            )
            //very bright color
//            collapsingToolbarLayout.setContentScrimColor(
//                palette.getLightVibrantColor(
//                    ContextCompat.getColor(
//                        applicationContext, R.color.colorPrimary
//                    )
//                )
//            )
            //this one doesn't seem to do anything.
//            collapsingToolbarLayout.setStatusBarScrimColor(
//                palette.getDarkMutedColor(
//                    ContextCompat.getColor(
//                        applicationContext, R.color.colorPrimaryDark
//                    )
//                )
//            )
        }
    }

    private fun toolbarTextAppearance() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar)
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
