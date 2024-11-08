package edu.cs4730.mvcviewsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.cs4730.mvcviewsdemo.databinding.ActivityMainBinding;

/**
 * A simple activity to demo the MVC method.  Uses viewBinding.
 */

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private ActivityMainBinding binding;

    //model data, myColors holds all the information.
    ColorList myColors = new ColorList();
    //ImageView, which also needs a bitmap and Canvas to draw display the colors
    Bitmap theColor;
    Canvas theColorc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //setup the picture to draw the color with a bitmap and the canvas to draw on
        theColor = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        theColorc = new Canvas(theColor);

        //setup the listeners
        binding.Next.setOnClickListener(this);
        binding.Prev.setOnClickListener(this);
        binding.Prev.setEnabled(false);  //can't go back yet.

        //setup first list
        binding.colorName.setText(myColors.getName());
        createImage();

    }

    /**
     * Simple methods to draw the color in the canvas (declared in oncreate) and put it in the imageview.
     */
    void createImage() {
        //drawColor fills the image with that color.
        theColorc.drawColor(myColors.getNum());
        binding.imageView1.setImageBitmap(theColor);
    }

    /**
     * Deals with the two buttons, next and previous.  also disables buttons when not necessary.
     */
    @Override
    public void onClick(View v) {
        if (v == binding.Next) {  //Next button
            myColors.next();
            binding.colorName.setText(myColors.getName());
            createImage();
        } else { //has to be Prev
            myColors.prev();
            binding.colorName.setText(myColors.getName());
            createImage();
        }
        //set the buttons correctly.
        binding.Next.setEnabled(!myColors.isLast());  //turn off when this is the last entry.
        binding.Prev.setEnabled(!myColors.isFirst());  //turn off when this is the first entry
    }
}