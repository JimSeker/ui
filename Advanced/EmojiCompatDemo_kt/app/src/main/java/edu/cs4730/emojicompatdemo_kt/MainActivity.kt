package edu.cs4730.emojicompatdemo_kt

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.provider.FontRequest
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import java.lang.ref.WeakReference

/**
 * shows how to make sure the app is using the most current emojiis.
 *
 * note, with the AppCompat version 1.4.0+  it uses the newer emoji2 library by default, so it just works.
 */

class MainActivity : AppCompatActivity() {

    /** Change this to `false` when you want to use the downloadable Emoji font.  */
    private val USE_BUNDLED_EMOJI = false
    private val TAG = "MainActivity"

    companion object {
        //avocado  U+1f951
        private val avocado = "\uD83E\uDD51"

        //http://www.iemoji.com/view/emoji/2487/smileys-people/star-struck
        //give the unicode which is  	U+1F929  and the C/C++/Java Src "\uD83E\uDD29" for the emoji
        private val starstruck = "\uD83E\uDD29"


        val EMOJI = "$avocado $starstruck"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //you MUST do this before the setcontentview otherwise, it will forceclose.
        //use the bundled fonts or download them.
        val config: EmojiCompat.Config
        config = if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            BundledEmojiCompatConfig(applicationContext)
        } else {
            // Use a downloadable font for EmojiCompat
            val fontRequest: FontRequest = FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs
            )
            FontRequestEmojiCompatConfig(applicationContext, fontRequest)
                .setReplaceAll(true)
                .setEmojiSpanIndicatorEnabled(true)
                .setEmojiSpanIndicatorColor(Color.GREEN)
                .registerInitCallback(object : EmojiCompat.InitCallback() {
                    override fun onInitialized() {
                        Log.i(TAG, "EmojiCompat initialized")
                    }

                    override fun onFailed(throwable: Throwable?) {
                        Log.e(TAG, "EmojiCompat initialization failed", throwable)
                    }
                })
        }
        EmojiCompat.init(config)
        //now we can use the fonts and
        setContentView(R.layout.activity_main)


        // TextView variant provided by EmojiCompat library
        val emojiTextView = findViewById<TextView>(R.id.emoji_text_view)
        emojiTextView.text = getString(R.string.emoji_text_view, EMOJI)

        // EditText variant provided by EmojiCompat library
        val emojiEditText = findViewById<TextView>(R.id.emoji_edit_text)
        emojiEditText.text = getString(R.string.emoji_edit_text, EMOJI)

        // Button variant provided by EmojiCompat library
        val emojiButton = findViewById<TextView>(R.id.emoji_button)
        emojiButton.text = getString(R.string.emoji_button, EMOJI)
        emojiButton.setOnClickListener { //note the logger prints the emojis, I thought it would print the code instead.
            Log.d(TAG, "it's this" + emojiEditText.text.toString())
        }

        // Regular TextView without EmojiCompat support; you have to manually process the text
        val regularTextView = findViewById<TextView>(R.id.regular_text_view)
        EmojiCompat.get().registerInitCallback(InitCallback(regularTextView))

        // Custom TextView
        val customTextView = findViewById<TextView>(R.id.emoji_custom_text_view)
        customTextView.text = getString(R.string.custom_text_view, EMOJI)
    }

    private class InitCallback internal constructor(regularTextView: TextView) :
        EmojiCompat.InitCallback() {
        private val mRegularTextViewRef: WeakReference<TextView>
        override fun onInitialized() {
            val regularTextView = mRegularTextViewRef.get()
            if (regularTextView != null) {
                val compat = EmojiCompat.get()
                val context = regularTextView.context
                regularTextView.text =
                    compat.process(
                        context.getString(
                            R.string.regular_text_view,
                            EMOJI
                        )
                    )
            }
        }

        init {
            mRegularTextViewRef = WeakReference(regularTextView)
        }
    }

}