package edu.cs4730.supportdesigndemo3_kt

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.google.android.material.snackbar.Snackbar
import edu.cs4730.supportdesigndemo3_kt.databinding.ActivityMainBinding


/**
 * example mostly shows a collapsing toolbar, which is in the xml
 * it also changes the status and toolbar color as well, plus the text size as the toolbar is collapsing.
 *
 * Some code from http://www.tutorialsbuzz.com/2015/11/android-collapsingtoolbarlayout-example_7.html
 * and http://antonioleiva.com/collapsing-toolbar-layout/
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.collapsingToolbar.title = resources.getString(R.string.app_name)
        binding.fab.setOnClickListener { view ->
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
            binding.collapsingToolbar.setContentScrimColor(
                palette!!.getDarkVibrantColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.colorPrimary
                    )
                )
            )
            //a muted color   mostly gray
//            binding.collapsingToolbar.setContentScrimColor(
//                palette.getMutedColor(
//                    ContextCompat.getColor(
//                        applicationContext, R.color.colorPrimary
//                    )
//                )
//            )
            //dark muted color  very dark blue, almost black.
//            binding.collapsingToolbar.setContentScrimColor(
//                palette.getDarkMutedColor(
//                    ContextCompat.getColor(
//                        applicationContext, R.color.colorPrimary
//                    )
//                )
//            )
            //very bright color
//            binding.collapsingToolbar.setContentScrimColor(
//                palette.getLightVibrantColor(
//                    ContextCompat.getColor(
//                        applicationContext, R.color.colorPrimary
//                    )
//                )
//            )
            //this one doesn't seem to do anything.
//            binding.collapsingToolbar.setStatusBarScrimColor(
//                palette.getDarkMutedColor(
//                    ContextCompat.getColor(
//                        applicationContext, R.color.colorPrimaryDark
//                    )
//                )
//            )
        }
    }

    private fun toolbarTextAppearance() {
        binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar)
        binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar)
    }

}
