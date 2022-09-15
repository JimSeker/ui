package edu.cs4730.supportpalettedemo_kt

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.Swatch


/**
 * https://github.com/codepath/android_guides/wiki/Dynamic-Color-using-Palettes
 * http://willowtreeapps.com/blog/palette-the-new-api-for-android/
 * https://www.bignerdranch.com/blog/extracting-colors-to-a-palette-with-android-lollipop/
 */
class MainActivity : AppCompatActivity() {
    lateinit var iv_vibrant: TextView
    lateinit var iv_vibrantdark: TextView
    lateinit var iv_vibrantlight: TextView
    lateinit var iv_muted: TextView
    lateinit var iv_muteddark: TextView
    lateinit var iv_mutedlight: TextView
    lateinit var ib: ImageButton
    var which = 1
    lateinit var myBitmap: Bitmap
    var mySwatch: Swatch? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ib = findViewById(R.id.imageView)
        ib.setOnClickListener {
            which++
            if (which > 4) which = 1
            setup()
        }
        iv_vibrant = findViewById(R.id.iv_vibrant)
        iv_vibrantdark = findViewById(R.id.iv_vibrantdark)
        iv_vibrantlight = findViewById(R.id.iv_vibrantlight)
        iv_muted = findViewById(R.id.iv_muted)
        iv_muteddark = findViewById(R.id.iv_muteddark)
        iv_mutedlight = findViewById(R.id.iv_mutedlight)
        setup()
    }

    fun setup() {
        when (which) {
            1 -> myBitmap = BitmapFactory.decodeResource(resources, R.drawable.nyancat)
            2 -> myBitmap = BitmapFactory.decodeResource(resources, R.drawable.pic2)
            3 -> myBitmap = BitmapFactory.decodeResource(resources, R.drawable.pic3)
            4 -> myBitmap = BitmapFactory.decodeResource(resources, R.drawable.pic4)
        }
        ib.setImageBitmap(myBitmap)
        if (!myBitmap.isRecycled) {
            //synchronous method, which does work on the main thread and is not recommended
            //  Palette palette = new Palette.Builder(myBitmap).generate();
            //Aysnchronous method.  Since I didn't use the variable, just the method in the listener, I didn't delcare a variable for it  This line commented out shows the full declaration if needed.
            //AsyncTask<Bitmap, Void, Palette> palette = new Palette.Builder(myBitmap).generate(new Palette.PaletteAsyncListener() {
            Palette.Builder(myBitmap).generate { palette ->
                mySwatch = palette!!.vibrantSwatch
                if (mySwatch != null) {
                    iv_vibrant.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    iv_vibrant.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    iv_vibrant.setTextColor(Color.BLACK)
                    iv_vibrant.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.darkVibrantSwatch
                if (mySwatch != null) {
                    iv_vibrantdark.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    iv_vibrantdark.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    iv_vibrantdark.setTextColor(Color.BLACK)
                    iv_vibrantdark.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.lightVibrantSwatch
                if (mySwatch != null) {
                    iv_vibrantlight.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    iv_vibrantlight.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    iv_vibrantlight.setTextColor(Color.BLACK)
                    iv_vibrantlight.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.mutedSwatch
                if (mySwatch != null) {
                    iv_muted.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    iv_muted.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    iv_muted.setTextColor(Color.BLACK)
                    iv_muted.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.darkMutedSwatch
                if (mySwatch != null) {
                    iv_muteddark.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    iv_muteddark.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    iv_muteddark.setTextColor(Color.BLACK)
                    iv_muteddark.setBackgroundColor(Color.WHITE)
                }
                mySwatch = palette.lightMutedSwatch
                if (mySwatch != null) {
                    iv_mutedlight.setTextColor(mySwatch!!.bodyTextColor) //set textcolor which will be easy to read on the background color.
                    iv_mutedlight.setBackgroundColor(mySwatch!!.rgb) //set background color, which is the color
                } else {
                    iv_mutedlight.setTextColor(Color.BLACK)
                    iv_mutedlight.setBackgroundColor(Color.WHITE)
                }
            }
        }
    }
}
