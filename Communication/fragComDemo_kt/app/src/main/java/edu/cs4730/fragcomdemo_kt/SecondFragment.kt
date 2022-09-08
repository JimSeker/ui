package edu.cs4730.fragcomdemo_kt

import android.widget.TextView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import java.lang.ClassCastException

/**
 * A simple [Fragment] subclass. Activities that contain this fragment
 * must implement the [OnFragmentInteractionListener2]
 * interface to handle interaction events. Use the
 * [SecondFragment.newInstance] factory method to create an instance of
 * this fragment.
 */
class SecondFragment : Fragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mListener: OnFragmentInteractionListener2? = null
    lateinit var tv1: TextView
    lateinit var tv2: TextView
    lateinit var btn1: Button
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
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_second, container, false)
        tv1 = myView.findViewById(R.id.sf_tv1)
        tv1.text = "Parameter1: $mParam1"
        tv2 = myView.findViewById(R.id.sf_tv2)
        tv2.text = "Parameter2: $mParam2"
        btn1 = myView.findViewById(R.id.sf_btn1)
        btn1.setOnClickListener {
            if (mListener != null) {
                //this is calling the interface, which call into the activity, so it
                //can change to the first fragment and send a simple string as well.
                mListener!!.onFragmentInteraction2("Called by SecondFramgnet")
            }
        }
        return myView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity = requireActivity()
        mListener = try {
            activity as OnFragmentInteractionListener2
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
    interface OnFragmentInteractionListener2 {
        fun onFragmentInteraction2(Data: String)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of this fragment using
         * the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        fun newInstance(param1: String, param2: String): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}