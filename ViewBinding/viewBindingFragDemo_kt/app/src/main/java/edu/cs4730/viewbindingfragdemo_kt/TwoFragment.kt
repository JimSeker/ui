package edu.cs4730.viewbindingfragdemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.cs4730.viewbindingfragdemo_kt.databinding.FragmentTwoBinding


/**
 * simple fragment has a button.  using the viewbinding so you
 * don't need findviewbyid.  and you know everything is not null, plus this
 * also has an advantage you have the same id names in different xml and not have an issues
 * with most studio picking the wrong one.
 */
class TwoFragment : Fragment() {
    private var binding: FragmentTwoBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        binding!!.button.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                requireContext(),
                "Hi there",
                Toast.LENGTH_SHORT
            ).show()
        })


        //needs a view so return one.
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}