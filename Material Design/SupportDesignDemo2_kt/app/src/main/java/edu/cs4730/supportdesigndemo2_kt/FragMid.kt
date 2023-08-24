package edu.cs4730.supportdesigndemo2_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.supportdesigndemo2_kt.databinding.MiddleBinding

/**
 * This is the middle fragment, which uses a edittext to put text on the either the left or right
 * fragment in the view pager. It uses a modelview to start the data so the fragments can get via
 * an observer.
 */
class FragMid : Fragment(), View.OnClickListener {
    lateinit var binding: MiddleBinding
    lateinit var mViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = MiddleBinding.inflate(inflater, container, false)
        binding.btnLt.setOnClickListener(this)
        binding.btnRt.setOnClickListener(this)
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        return binding.root
    }

    override fun onClick(v: View) {
        if (v === binding.btnLt) { //left button
            mViewModel.appendStrLeft(binding.editText1.text.toString())
        } else {  //right button
            mViewModel.appendStrRight(binding.editText1.text.toString())
        }
        binding.editText1.setText("")
    }
}