package edu.cs4730.navdrawerfragviewmodeldemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import edu.cs4730.navdrawerfragviewmodeldemo.databinding.ListfragmentLayoutBinding;

/**
 * this ia listfragment.  All we need to do is setlistadapter in onCreateView
 * and override onListItemClick.    using the viewmodel setup the data.
 */

public class titlefrag extends ListFragment {

    DataViewModel mViewModel;
    private ListfragmentLayoutBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = ListfragmentLayoutBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, Shakespeare.TITLES);
        setListAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onListItemClick(@NonNull ListView listView, @NonNull View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mViewModel.setItem((int) id);
    }


}
