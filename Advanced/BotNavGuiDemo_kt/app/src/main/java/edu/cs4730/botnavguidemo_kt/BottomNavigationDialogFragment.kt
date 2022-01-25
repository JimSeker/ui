package edu.cs4730.botnavguidemo_kt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import java.lang.ClassCastException

class BottomNavigationDialogFragment : BottomSheetDialogFragment() {
    private var mListener: OnFragmentInteractionListener? = null

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
        val myView: View = inflater.inflate(R.layout.fragment_bottomsheet, container, false)
        val nw: NavigationView = myView.findViewById(R.id.navigation_view)
        nw.setNavigationItemSelectedListener { menuItem ->
            mListener!!.onFragmentPicker(menuItem.itemId)
            dismiss()
            false
        }
        return myView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity? = activity
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
