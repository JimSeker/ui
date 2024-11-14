package edu.cs4730.supportpalettedemo_kt

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.Swatch
import edu.cs4730.supportpalettedemo_kt.databinding.ActivityMainBinding


/**
 * https://github.com/codepath/android_guides/wiki/Dynamic-Color-using-Palettes
 * http://willowtreeapps.com/blog/palette-the-new-api-for-android/
 * https://www.bignerdranch.com/blog/extracting-colors-to-a-palette-with-android-lollipop/
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var which = 1
    lateinit var myBitmap: Bitmap
    var mySwatch: Swatch? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        binding.imageView.setOnClickListener {
            which++
            if (which > 4) which = 1
            setup()
        }
        setup()
    }

    fun setup() {
        when (which) {
            1 -> myBitmap = BitmapFactory.decodeResource(resources, R.drawable.nyancat)
            2 -> myBitmap = BitmapFactory.decodeResource(resources, R.drawable.pic2)
            3 -> myBitmap = BitmapFactory.decodeResource(resources, R.drawable.pic3)
            4 -> myBitmap = BitmapFactory.decodeResource(resources, R.drawable.pic4)
        }
        binding.imageView.setImageBitmap(myBitmap)
        if (!myBitmap.isRecycled) {
            //synchronous method, which does work on the main thread and is not recommended
            //  Palette palette = new Palette.Builder(myBitmap).generate();
            //Asynchronous method.  Since I didn't use the variable, just the method in the listener, I didn't declare a variable for it  This line commented out shows the full declaration if needed.
            //AsyncTask<Bitmap, Void, Palette> palette = new Palette.Builder(myBitmap).generate(new Palette.PaletteAsyncListener() {
            Palette.Builder(myBitmap).generate { palette ->
                mySwatch = palette!!.vibrantSwatch
                if (mySwatch != null) {
                    binding.ivVibrant.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    binding.ivVibrant.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    binding.ivVibrant.setTextColor(Color.BLACK)
                    binding.ivVibrant.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.darkVibrantSwatch
                if (mySwatch != null) {
                    binding.ivVibrantdark.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    binding.ivVibrantdark.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    binding.ivVibrantdark.setTextColor(Color.BLACK)
                    binding.ivVibrantdark.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.lightVibrantSwatch
                if (mySwatch != null) {
                    binding.ivVibrantlight.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    binding.ivVibrantlight.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    binding.ivVibrantlight.setTextColor(Color.BLACK)
                    binding.ivVibrantlight.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.mutedSwatch
                if (mySwatch != null) {
                    binding.ivMuted.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    binding.ivMuted.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    binding.ivMuted.setTextColor(Color.BLACK)
                    binding.ivMuted.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.darkMutedSwatch
                if (mySwatch != null) {
                    binding.ivMuteddark.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    binding.ivMuteddark.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    binding.ivMuteddark.setTextColor(Color.BLACK)
                    binding.ivMuteddark.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.lightMutedSwatch
                if (mySwatch != null) {
                    binding.ivMutedlight.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    binding.ivMutedlight.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    binding.ivMutedlight.setTextColor(Color.BLACK)
                    binding.ivMutedlight.setBackgroundColor(Color.WHITE)
                }
            }
        }
    }
}
