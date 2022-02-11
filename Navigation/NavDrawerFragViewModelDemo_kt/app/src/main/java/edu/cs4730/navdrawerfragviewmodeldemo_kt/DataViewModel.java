package edu.cs4730.navdrawerfragviewmodeldemo_kt;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * simple viewmodel to pass information between the two fragments.
 */

public class DataViewModel extends ViewModel {
    private MutableLiveData<Integer> item = new MutableLiveData<Integer>(0);

    LiveData<Integer> getData() {
        return item;
    }

    int getItem() { return item.getValue();}

    void setItem(int i) { item.setValue(i);}

}
