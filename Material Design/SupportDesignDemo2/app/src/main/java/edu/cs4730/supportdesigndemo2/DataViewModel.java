package edu.cs4730.supportdesigndemo2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/*
 * this is class to hold data so even hen the destroyview is called for the fragment
 * the data can survive.   The left and right fragment are creating their own instance of this object.
 */

public class DataViewModel extends ViewModel {
    private MutableLiveData<String> data;

    public LiveData<String> getData() {
        if (data == null) {
            data = new MutableLiveData<String>();
            data.setValue("");
        }
        return data;
    }

    public String getStr() {
        if (data == null)
            data = new MutableLiveData<String>();
        return data.getValue();
    }

    public void setStr(String item) {
        if (data == null)
            data = new MutableLiveData<String>();
        data.setValue(item);
    }

    public void appendStr(String item) {
        if (data == null)
            data = new MutableLiveData<String>();
        String prev = data.getValue();
        data.setValue(prev + "\n" + item);
    }
}
