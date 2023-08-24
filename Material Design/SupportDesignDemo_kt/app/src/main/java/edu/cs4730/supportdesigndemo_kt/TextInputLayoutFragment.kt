package edu.cs4730.supportdesigndemo_kt

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import androidx.fragment.app.Fragment
import edu.cs4730.supportdesigndemo_kt.databinding.FragmentTextinputlayoutBinding

/**
 * A simple example to show how to use the text input layout for a editText.
 * in the xml the textinputlayout is wrapped around the edittext.  it will display the hint
 * even after the user starts typing and allows you to set an error message as well.
 */
class TextInputLayoutFragment : Fragment() {
    lateinit var binding: FragmentTextinputlayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTextinputlayoutBinding.inflate(inflater, container, false)

        binding.edittext02.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString() != "abc") {
                    //set the error message that will display below the edittext
                    binding.textinput02.error = "Incorrect input."
                } else {
                    binding.textinput02.error = "" //clear the error message.
                }
            }

            //I don't care about this methods...
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        })
        return binding.root
    }
}