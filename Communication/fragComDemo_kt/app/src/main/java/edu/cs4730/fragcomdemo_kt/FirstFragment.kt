package edu.cs4730.fragcomdemo_kt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cs4730.fragcomdemo_kt.FirstFragment.OnFragmentInteractionListener1
import edu.cs4730.fragcomdemo_kt.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass. Activities that contain this fragment
 * must implement the [OnFragmentInteractionListener1]
 * interface to handle interaction events. Use the
 * [FirstFragment.newInstance] factory method to create an instance of
 * this fragment.
 */
class FirstFragment : Fragment() {
    private lateinit var mParam1: String
    private lateinit var mParam2: String
    private var mListener: OnFragmentInteractionListener1? = null
    lateinit var binding: FragmentFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1).toString()
            mParam2 = requireArguments().getString(ARG_PARAM2).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.ffTv1.text = "Parameter1: $mParam1"
        binding.ffTv2.text = "Parameter2: $mParam2"
        binding.ffBtn1.setOnClickListener {
            if (mListener != null) {
                //this is calling the interface, which call into the activity, so it
                //can change to the first fragment and send a simple string as well.
                mListener!!.onFragmentInteraction1("Called by FirstFramgnet")
            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity = requireActivity()
        mListener = try {
            activity as OnFragmentInteractionListener1
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$activity must implement OnFragmentInteractionListener"
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
    interface OnFragmentInteractionListener1 {
        fun onFragmentInteraction1(Data: String)
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
         * @return A new instance of fragment FirstFragment.
         */
        fun newInstance(param1: String, param2: String): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}