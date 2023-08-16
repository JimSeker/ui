package edu.cs4730.botnavguidemo_kt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import edu.cs4730.botnavguidemo_kt.databinding.FragmentBottomsheetBinding

class BottomNavigationDialogFragment : BottomSheetDialogFragment() {
    private var mListener: OnFragmentInteractionListener? = null

    lateinit var  binding: FragmentBottomsheetBinding

    interface OnFragmentInteractionListener {
        fun onFragmentPicker(id: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomsheetBinding.inflate(inflater, container, false)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            mListener!!.onFragmentPicker(menuItem.itemId)
            dismiss()
            false
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity = requireActivity()
        mListener = try {
            activity as OnFragmentInteractionListener?
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
}
