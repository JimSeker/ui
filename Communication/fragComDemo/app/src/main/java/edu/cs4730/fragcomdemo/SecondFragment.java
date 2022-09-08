package edu.cs4730.fragcomdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link SecondFragment.OnFragmentInteractionListener2}
 * interface to handle interaction events. Use the
 * {@link SecondFragment#newInstance} factory method to create an instance of
 * this fragment.
 */
public class SecondFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener2 mListener;

    TextView tv1, tv2;
    Button btn1;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1);
            mParam2 = requireArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_second, container, false);
        tv1 = myView.findViewById(R.id.sf_tv1);
        tv1.setText("Parameter1: " + mParam1);
        tv2 = myView.findViewById(R.id.sf_tv2);
        tv2.setText("Parameter2: " + mParam2);
        btn1 = myView.findViewById(R.id.sf_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    //this is calling the interface, which call into the activity, so it
                    //can change to the first fragment and send a simple string as well.
                    mListener.onFragmentInteraction2("Called by SecondFramgnet");
                }
            }
        });

        return myView;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = requireActivity();
        try {
            mListener = (OnFragmentInteractionListener2) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener2 {
        public void onFragmentInteraction2(String Data);
    }
}
