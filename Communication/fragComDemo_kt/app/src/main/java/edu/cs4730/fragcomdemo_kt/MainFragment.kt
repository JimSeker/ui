package edu.cs4730.fragcomdemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import edu.cs4730.fragcomdemo_kt.databinding.FragmentMainBinding
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private var mListener: OnFragmentInteractionListener? = null
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        //button to call firstfragment
        binding.button1.setOnClickListener(View.OnClickListener {
            if (mListener != null) {
                //this is calling the interface, which call into the activity, so it
                //can change to the first fragment and send a simple string as well.
                mListener!!.onFragmentInteraction(1)
            }
        })
        //button to call secondfragment.
        binding.button2.setOnClickListener(View.OnClickListener {
            if (mListener != null) {
                //this is calling the interface, which call into the activity, so it
                //can change to the second fragment and send a simple string as well.
                mListener!!.onFragmentInteraction(2)
            }
        })
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity = requireActivity()
        mListener = try {
            activity as OnFragmentInteractionListener
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(which: Int)
    }
}