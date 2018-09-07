package edu.cs4730.modelviewrecyclerviewdemo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * a simple class to store the data string name is in.
 * <p>
 * note, lint is stupid and says they class should be private.  it can't be or it dies..
 */

public class DataViewModel extends ViewModel {
    private MutableLiveData<String> item = new MutableLiveData<String>();

    //note, no constructor seems to be allowed, or it dies.

    LiveData<String> getItemLD() {
        item.setValue("default");
        return item;
    }

    String getItem() {
        return item.getValue();
    }

    void setItem(String n) {
        Log.d("VM", "data is " + n);
        item.setValue(n);
    }
}
