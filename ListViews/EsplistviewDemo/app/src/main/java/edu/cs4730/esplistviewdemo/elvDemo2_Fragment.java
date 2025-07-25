package edu.cs4730.esplistviewdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.esplistviewdemo.databinding.Elvdemo2FragmentBinding;


/**
 * uses a simpleExpandableListAdapter without extending it.
 * lists are created from the group and child list in method below.
 */

public class elvDemo2_Fragment extends Fragment {

    String TAG = "elvDemo2_Fragment";

    SimpleExpandableListAdapter listAdapter;
   Elvdemo2FragmentBinding binding;
    ArrayList<Map<String, String>> listDataGroup;

    ArrayList<ArrayList<Map<String, String>>> listDataChild;
    //HashMap<String, List<String>>

    public elvDemo2_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = Elvdemo2FragmentBinding.inflate(inflater, container, false);

        prepareListData();

        // get the ExpandableListView from the layout.
        // for the layouts, we R.layout.  and R.id since we don't have the viewbinding setup for them.
        listAdapter = new SimpleExpandableListAdapter(
            requireContext(),                            //context
            listDataGroup,                  // group list in the form:  List<? extends Map<String, ?>>
            R.layout.evl2_group_row,        // Group item layout XML.
            new String[]{"Group Item"},  // the key of group item.   String[] groupFrom,
            new int[]{R.id.row_name},    // ID of each group item.-Data under the key goes into this TextView.  int[] groupTo
            listDataChild,              // childData describes second-level entries. in the form List<? extends List<? extends Map<String, ?>>>
            R.layout.evl2_child_row,             // Layout for sub-level entries(second level).
            new String[]{"Sub Item A", "Sub Item B"},      // Keys in childData maps to display.
            new int[]{R.id.grp_childA, R.id.grp_childB}     // Data under the keys above go into these TextViews.
        );
        binding.lvExp2.setAdapter(listAdapter);       // setting the adapter in the list.

        return binding.getRoot();
    }

    /**
     * Preparing the list data
     */
    private void prepareListData() {
        //create Group List
        listDataGroup = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 6; ++i) { // 6 groups........
            HashMap<String, String> m = new HashMap<String, String>();
            m.put("Group Item", "Group Item " + i); // the key and it's value.
            listDataGroup.add(m);
        }

        //create the child list, which is more complex
        listDataChild = new ArrayList<ArrayList<Map<String, String>>>();
        for (int i = 0; i < 6; ++i) {
            /* each group need each HashMap-Here for each group we have 3 subgroups */
            ArrayList<Map<String, String>> secList = new ArrayList<Map<String, String>>();
            for (int n = 0; n < 3; n++) {
                HashMap<String, String> child = new HashMap<String, String>();
                child.put("Sub Item A", "Sub ItemA " + n);
                child.put("Sub Item B", "Sub ItemB " + n);
                secList.add(child);
            }
            listDataChild.add(secList);
        }
    }

}
