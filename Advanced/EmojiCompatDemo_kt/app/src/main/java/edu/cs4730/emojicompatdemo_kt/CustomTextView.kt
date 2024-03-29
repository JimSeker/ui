package edu.cs4730.emojicompatdemo_kt

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.emoji.widget.EmojiTextViewHelper

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * A sample implementation of custom TextView.
 *
 * You can use [EmojiTextViewHelper] to make your custom TextView compatible with
 * EmojiCompat.
 */
class CustomTextView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatTextView(context!!, attrs, defStyleAttr) {
    private var mEmojiTextViewHelper: EmojiTextViewHelper? = null
    override fun setFilters(filters: Array<InputFilter>) {
        super.setFilters(emojiTextViewHelper.getFilters(filters))
    }

    override fun setAllCaps(allCaps: Boolean) {
        super.setAllCaps(allCaps)
        emojiTextViewHelper.setAllCaps(allCaps)
    }

    /**
     * Returns the [EmojiTextViewHelper] for this TextView.
     *
     *
     * This method can be called from super constructors through [ ][.setFilters] or [.setAllCaps].
     */
    private val emojiTextViewHelper: EmojiTextViewHelper
        private get() {
            if (mEmojiTextViewHelper == null) {
                mEmojiTextViewHelper = EmojiTextViewHelper(this)
            }
            return mEmojiTextViewHelper!!
        }

    init {
        emojiTextViewHelper.updateTransformationMethod()
    }
}
