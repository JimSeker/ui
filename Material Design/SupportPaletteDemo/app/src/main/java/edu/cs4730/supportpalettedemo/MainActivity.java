package edu.cs4730.supportpalettedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.palette.graphics.Palette;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import edu.cs4730.supportpalettedemo.databinding.ActivityMainBinding;

/**
 * https://github.com/codepath/android_guides/wiki/Dynamic-Color-using-Palettes
 * http://willowtreeapps.com/blog/palette-the-new-api-for-android/
 * https://www.bignerdranch.com/blog/extracting-colors-to-a-palette-with-android-lollipop/
 */
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int which = 1;
    Bitmap myBitmap;
    Palette.Swatch mySwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which++;
                if (which > 4) which = 1;
                setup();
            }
        });
        setup();

    }

    public void setup() {
        switch (which) {
            case 1:
                myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nyancat);
                break;
            case 2:
                myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic2);
                break;
            case 3:
                myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic3);
                break;
            case 4:
                myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic4);
                break;

        }

        binding.imageView.setImageBitmap(myBitmap);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            //synchronous method, which does work on the main thread and is not recommended
            //  Palette palette = new Palette.Builder(myBitmap).generate();
            //Asynchronous method.  Since I didn't use the variable, just the method in the listener, I didn't declare a variable for it  This line commented out shows the full declaration if needed.
            //AsyncTask<Bitmap, Void, Palette> palette = new Palette.Builder(myBitmap).generate(new Palette.PaletteAsyncListener() {
            new Palette.Builder(myBitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    mySwatch = palette.getVibrantSwatch();
                    if (mySwatch != null) {
                        binding.ivVibrant.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        binding.ivVibrant.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        binding.ivVibrant.setTextColor(Color.BLACK);
                        binding.ivVibrant.setBackgroundColor(Color.WHITE);
                    }

                    mySwatch = palette.getDarkVibrantSwatch();
                    if (mySwatch != null) {
                        binding.ivVibrantdark.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        binding.ivVibrantdark.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        binding.ivVibrantdark.setTextColor(Color.BLACK);
                        binding.ivVibrantdark.setBackgroundColor(Color.WHITE);
                    }

                    mySwatch = palette.getLightVibrantSwatch();
                    if (mySwatch != null) {
                        binding.ivVibrantlight.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        binding.ivVibrantlight.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        binding.ivVibrantlight.setTextColor(Color.BLACK);
                        binding.ivVibrantlight.setBackgroundColor(Color.WHITE);
                    }

                    mySwatch = palette.getMutedSwatch();
                    if (mySwatch != null) {
                        binding.ivMuted.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        binding.ivMuted.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        binding.ivMuted.setTextColor(Color.BLACK);
                        binding.ivMuted.setBackgroundColor(Color.WHITE);
                    }

                    mySwatch = palette.getDarkMutedSwatch();
                    if (mySwatch != null) {
                        binding.ivMuteddark.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        binding.ivMuteddark.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        binding.ivMuteddark.setTextColor(Color.BLACK);
                        binding.ivMuteddark.setBackgroundColor(Color.WHITE);
                    }
                    mySwatch = palette.getLightMutedSwatch();
                    if (mySwatch != null) {
                        binding.ivMutedlight.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        binding.ivMutedlight.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        binding.ivMutedlight.setTextColor(Color.BLACK);
                        binding.ivMutedlight.setBackgroundColor(Color.WHITE);
                    }
                }
            });

        }
    }
}
