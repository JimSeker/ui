package edu.cs4730.supportpalettedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/*
  https://github.com/codepath/android_guides/wiki/Dynamic-Color-using-Palettes
  http://willowtreeapps.com/blog/palette-the-new-api-for-android/
  https://www.bignerdranch.com/blog/extracting-colors-to-a-palette-with-android-lollipop/

 */
public class MainActivity extends AppCompatActivity {
    TextView iv_vibrant, iv_vibrantdark, iv_vibrantlight, iv_muted, iv_muteddark, iv_mutedlight;
    ImageButton ib;
    int which = 1;
    Bitmap myBitmap;
    Palette.Swatch mySwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ib = (ImageButton) findViewById(R.id.imageView);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                which++;
                if (which > 4) which = 1;
                setup();
            }
        });


        iv_vibrant = (TextView) findViewById(R.id.iv_vibrant);
        iv_vibrantdark = (TextView) findViewById(R.id.iv_vibrantdark);
        iv_vibrantlight = (TextView) findViewById(R.id.iv_vibrantlight);
        iv_muted = (TextView) findViewById(R.id.iv_muted);
        iv_muteddark = (TextView) findViewById(R.id.iv_muteddark);
        iv_mutedlight = (TextView) findViewById(R.id.iv_mutedlight);

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

        ib.setImageBitmap(myBitmap);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            //synchronous method, which does work on the main thread and is not recommended
          //  Palette palette = new Palette.Builder(myBitmap).generate();
            //Aysnchronous method.  Since I didn't use the variable, just the method in the listener, I didn't delcare a variable for it  This line commented out shows the full declaration if needed.
            //AsyncTask<Bitmap, Void, Palette> palette = new Palette.Builder(myBitmap).generate(new Palette.PaletteAsyncListener() {
            new Palette.Builder(myBitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    mySwatch = palette.getVibrantSwatch();
                    if (mySwatch != null) {
                        iv_vibrant.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        iv_vibrant.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        iv_vibrant.setTextColor(Color.BLACK);
                        iv_vibrant.setBackgroundColor(Color.WHITE);
                    }

                    mySwatch = palette.getDarkVibrantSwatch();
                    if (mySwatch != null) {
                        iv_vibrantdark.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        iv_vibrantdark.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        iv_vibrantdark.setTextColor(Color.BLACK);
                        iv_vibrantdark.setBackgroundColor(Color.WHITE);
                    }

                    mySwatch = palette.getLightVibrantSwatch();
                    if (mySwatch != null) {
                        iv_vibrantlight.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        iv_vibrantlight.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        iv_vibrantlight.setTextColor(Color.BLACK);
                        iv_vibrantlight.setBackgroundColor(Color.WHITE);
                    }

                    mySwatch = palette.getMutedSwatch();
                    if (mySwatch != null) {
                        iv_muted.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        iv_muted.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        iv_muted.setTextColor(Color.BLACK);
                        iv_muted.setBackgroundColor(Color.WHITE);
                    }

                    mySwatch = palette.getDarkMutedSwatch();
                    if (mySwatch != null) {
                        iv_muteddark.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        iv_muteddark.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        iv_muteddark.setTextColor(Color.BLACK);
                        iv_muteddark.setBackgroundColor(Color.WHITE);
                    }
                    mySwatch = palette.getLightMutedSwatch();
                    if (mySwatch != null) {
                        iv_mutedlight.setTextColor(mySwatch.getBodyTextColor());  //set textcolor which will be easy to read on the background color.
                        iv_mutedlight.setBackgroundColor(mySwatch.getRgb());  //set background color, which is the color
                    } else {
                        iv_mutedlight.setTextColor(Color.BLACK);
                        iv_mutedlight.setBackgroundColor(Color.WHITE);
                    }
                }
            });

        }
    }
}
