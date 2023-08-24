package edu.cs4730.supportdesignbottomnavdemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cs4730.supportdesignbottomnavdemo_kt.databinding.FragmentBlankBinding

/**
 * This is a blank and generic fragment.  It doesn't actually do anything.  It's the generic
 * one that studio provided.  I just needed it to display a different message in the text view.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    lateinit var binding: FragmentBlankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        binding.label1.text = mParam1
        return binding.root
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): BlankFragment {
            val fragment = BlankFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}