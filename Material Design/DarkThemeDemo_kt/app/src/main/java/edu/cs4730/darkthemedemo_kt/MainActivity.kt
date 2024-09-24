package edu.cs4730.darkthemedemo_kt

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.cs4730.darkthemedemo_kt.databinding.ActivityMainBinding


/**
 * This example is how to change themes dynamically and use to use the DayNight theme in
 * appcompat and material design.
 *
 * The code here just changes the themes.  The default is the light theme.   See the styles.xml
 * file in /values for the 6 themes.  I went with the defaults for all 6 of them.
 *
 * A note the Theme can only be set before the super.onCreate(), so preferences are used so when the
 * app start it can set the theme.
 *
 * Need add the DayNight (both app and material?) stuff for AppCompatDelegate.setDefaultNightMode()
 * with MODE_LIGHT_NO/YES and MODE_NIGHT_AUTO_BATTERY  System default - MODE_NIGHT_FOLLOW_SYSTEM
 *
 *
 *  don't change the commit() to apply() or the app will crash a lot.  need to finish the commit before
 *  the app restarts the drawing.  the kotlin version still crashs a lot more then java version.
 *  likely it's trying to be helpful and which is stupid.
 *
 *  material3 stuff, helpful https://m3.material.io/blog/migrating-material-3
 *  note going from material3 to appCompat will likely crash the app, restart and it works fine.
 */
class MainActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    var mytheme :Int  = 0
    lateinit var binding: ActivityMainBinding

    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        preferences = getSharedPreferences("example", MODE_PRIVATE)
        mytheme = preferences.getInt("Theme", R.style.AppTheme)
        setTheme(mytheme) //must be fore the super!
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme)
            editor.commit()
            recreate()
        }
        binding.button1.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme1)
            editor.commit()
            recreate()
        }
        binding.button2.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme2)
            editor.commit()
            recreate()
        }
        binding.button3.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme3)
            editor.commit()
            recreate()
        }
        binding.button4.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme4)
            editor.commit()
            recreate()
        }
        binding.button5.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme5)
            editor.commit()
            recreate()
        }
        binding.button6.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme6)
            editor.commit()
            recreate()
        }
        binding.button7.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme7)
            editor.commit()
            recreate()
        }
        binding.button8.setOnClickListener {
            val editor = preferences.edit()
            editor.putInt("Theme", R.style.AppTheme8)
            editor.commit()
            recreate()
        }
    }
}
