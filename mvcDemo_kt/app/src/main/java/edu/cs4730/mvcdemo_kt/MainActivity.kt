package edu.cs4730.mvcdemo_kt

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * A simple activity to demo the MVC method.
 *
 * For info on the constraint-layout see  https://developer.android.com/training/constraint-layout/index.html
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    //model data, myColors holds all the information.
    var myColors = ColorList()

    //ImageView, which also needs a bitmap and Canvas to draw display the colors
    lateinit var myPic: ImageView
    lateinit var theColor: Bitmap
    lateinit var theColorc: Canvas

    // The rest of the layout variables, buttons and text field.
    lateinit var Next: Button
    lateinit var Prev: Button
    lateinit var colorName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        //setup all the listeners and variables for the layout
        colorName = findViewById(R.id.Name)

        //setup the picture stuff.
        myPic = findViewById(R.id.imageView1)
        //to draw the color with a bitmap and the canvas to draw on
        theColor = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        theColorc = Canvas(theColor)

        //buttons and listeners
        Next = findViewById(R.id.Next)
        Next.setOnClickListener(this)
        Prev = findViewById(R.id.Prev)
        Prev.setOnClickListener(this)
        Prev.isEnabled = false //can't go back yet.

        //setup first list
        colorName.text = myColors.name
        createImage()
    }

    /**
     * Simple methods to draw the color in the canvas (declared in oncreate) and put it in the imageview.
     */
    fun createImage() {
        //drawColor fills the image with that color.
        theColorc.drawColor(myColors.getNum())
        myPic.setImageBitmap(theColor)
    }

    /**
     * (non-Javadoc)
     * see android.view.View.OnClickListener#onClick(android.view.View)
     *
     * Deals with the two buttons, next and previous.  also disables buttons when not necessary.
     */
    override fun onClick(v: View) {
        if (v === Next) {  //Next button
            myColors.next()
            colorName.text = myColors.name
            createImage()
        } else { //has to be Prev
            myColors.prev()
            colorName.text = myColors.name
            createImage()
        }
        //set the buttons correctly.
        Next.isEnabled = !myColors.isLast //turn off when this is the last entry.
        Prev.isEnabled = !myColors.isFirst //turn off when this is the first entry
    }
}
