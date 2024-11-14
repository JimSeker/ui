package edu.cs4730.supportdesigndemo3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.palette.graphics.Palette;

import edu.cs4730.supportdesigndemo3.databinding.ActivityMainBinding;

/**
 * example mostly shows a collapsing toolbar, which is in the xml
 * it also changes the status and toolbar color as well, plus the text size as the toolbar is collapsing.
 * <p>
 * Some code from http://www.tutorialsbuzz.com/2015/11/android-collapsingtoolbarlayout-example_7.html
 * and http://antonioleiva.com/collapsing-toolbar-layout/
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        binding.collapsingToolbar.setTitle(getResources().getString(R.string.app_name));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        dynamicToolbarColor();

        toolbarTextAppearance();
    }


    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.wolf_gray);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                //a dark color  a dark blue
                binding.collapsingToolbar.setContentScrimColor(palette.getDarkVibrantColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
                //a muted color   mostly gray
                //binding.collapsingToolbar.setContentScrimColor(palette.getMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
                //dark muted color  very dark blue, almost black.
                // binding.collapsingToolbar.setContentScrimColor(palette.getDarkMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
                //very bright color
                //binding.collapsingToolbar.setContentScrimColor(palette.getLightVibrantColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
                //this one doesn't seem to do anything.
                //binding.collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)));

            }
        });
    }


    private void toolbarTextAppearance() {
        binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }
}
