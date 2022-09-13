package edu.cs4730.simplefragcomdemo_kt

import edu.cs4730.simplefragcomdemo_kt.MainFragment.OnFragmentInteractionListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class MainFragment : Fragment() {
    private var mListener: OnFragmentInteractionListener? = null
    lateinit var myButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_main, container, false)
        myButton = myView.findViewById(R.id.button01)
        myButton.setOnClickListener {
            if (mListener != null) {
                mListener!!.onFragmentInteraction(1)
            }
        }
        return myView
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
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(Num: Int)
    }
}