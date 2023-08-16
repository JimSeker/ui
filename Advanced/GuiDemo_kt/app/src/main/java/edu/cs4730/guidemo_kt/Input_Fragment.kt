package edu.cs4730.guidemo_kt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.text.TextWatcher
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import edu.cs4730.guidemo_kt.databinding.InputFragmentBinding

class Input_Fragment : Fragment(), View.OnClickListener {
    var TAG = "Input_fragment"
    lateinit var binding: InputFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = InputFragmentBinding.inflate(inflater, container, false)

        binding.etPwd.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                    //doing nothing.
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // do nothing here.
                }

                override fun afterTextChanged(s: Editable) {
                    // When the text is changed.
                    Toast.makeText(requireContext(), binding.etPwd.text, Toast.LENGTH_SHORT).show()
                }
            }
        )
        binding.button.setOnClickListener(this)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onClick(v: View) {
        Toast.makeText(requireContext(), binding.etSingle.text, Toast.LENGTH_LONG).show()
    }
}