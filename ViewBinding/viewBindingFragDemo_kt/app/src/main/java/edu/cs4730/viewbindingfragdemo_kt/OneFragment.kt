package edu.cs4730.viewbindingfragdemo_kt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cs4730.viewbindingfragdemo_kt.databinding.FragmentOneBinding

/**
 * simple fragment has have a edittext and button.  using the viewbinding so you
 * don't need findviewbyid.  and you know everything is not null, plus this
 * also has an advantage you have the same id names in different xml and not have an issues
 * with most studio picking the wrong one.
 */
class OneFragment : Fragment() {
    private lateinit var binding: FragmentOneBinding
    private var mListener: OnFragmentInteractionListener1? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        binding.btnSubmit.setOnClickListener {
            if (mListener != null) {
                var value = "stuff"
                if (binding.etName.text.toString().compareTo("") != 0)
                    value = binding.etName.text.toString()
                mListener!!.onFragmentInteraction1(value)
            }
        }

        //needs to return a view.  so get it for the return
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity = requireActivity()
        mListener = try {
            activity as OnFragmentInteractionListener1
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener1 {
        fun onFragmentInteraction1(Data: String?)
    }
}