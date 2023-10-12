package edu.cs4730.emojicompatdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.provider.FontRequest;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.text.FontRequestEmojiCompatConfig;

import edu.cs4730.emojicompatdemo.databinding.ActivityMainBinding;

/**
 * show how to make sure the app is using the most current emojiis.
 * <p>
 * note, with the AppCompat version 1.4.0+  it uses the newer emoji2 library by default, so it just works.
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Change this to {@code false} when you want to use the downloadable Emoji font.
     */
    private static final boolean USE_BUNDLED_EMOJI = false;
    private static final String TAG = "MainActivity";

    //avocado  U+1f951
    private static final String avocado = "\uD83E\uDD51";

    //http://www.iemoji.com/view/emoji/2487/smileys-people/star-struck
    //give the unicode which is  	U+1F929  and the C/C++/Java Src "\uD83E\uDD29" for the emoji
    private static final String starstruck = "\uD83E\uDD29";

    static final String EMOJI = avocado + " " + starstruck;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //you MUST do this before the setcontentview otherwise, it will force close.
        //use the bundled fonts or download them.
        EmojiCompat.Config config;
        if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            config = new BundledEmojiCompatConfig(getApplicationContext());
        } else {
            // Use a downloadable font for EmojiCompat
            final FontRequest fontRequest = new FontRequest("com.google.android.gms.fonts", "com.google.android.gms", "Noto Color Emoji Compat", R.array.com_google_android_gms_fonts_certs);
            config = new FontRequestEmojiCompatConfig(getApplicationContext(), fontRequest).setReplaceAll(true).setEmojiSpanIndicatorEnabled(true).setEmojiSpanIndicatorColor(Color.GREEN).registerInitCallback(new EmojiCompat.InitCallback() {
                @Override
                public void onInitialized() {
                    Log.i(TAG, "EmojiCompat initialized");
                }

                @Override
                public void onFailed(@Nullable Throwable throwable) {
                    Log.e(TAG, "EmojiCompat initialization failed", throwable);
                }
            });
        }
        EmojiCompat.init(config);
        //now we can use the fonts and
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // TextView variant provided by EmojiCompat library
        binding.emojiTextView.setText(getString(R.string.emoji_text_view, EMOJI));

        // EditText variant provided by EmojiCompat library
        binding.emojiEditText.setText(getString(R.string.emoji_edit_text, EMOJI));

        // Button variant provided by EmojiCompat library
        binding.emojiButton.setText(getString(R.string.emoji_button, EMOJI));
        binding.emojiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //note the logger prints the emojis, I thought it would print the code instead.
                Log.d(TAG, "it's this" + binding.emojiEditText.getText().toString());
            }
        });

        // Regular TextView without EmojiCompat support; you have to manually process the text
        EmojiCompat.get().registerInitCallback(new InitCallback(binding.regularTextView));

        // Custom TextView
        binding.emojiCustomTextView.setText(getString(R.string.custom_text_view, EMOJI));

    }

    private static class InitCallback extends EmojiCompat.InitCallback {

        private final WeakReference<TextView> mRegularTextViewRef;

        InitCallback(TextView regularTextView) {
            mRegularTextViewRef = new WeakReference<>(regularTextView);
        }

        @Override
        public void onInitialized() {
            final TextView regularTextView = mRegularTextViewRef.get();
            if (regularTextView != null) {
                final EmojiCompat compat = EmojiCompat.get();
                final Context context = regularTextView.getContext();
                regularTextView.setText(compat.process(context.getString(R.string.regular_text_view, EMOJI)));
            }
        }

    }
}
