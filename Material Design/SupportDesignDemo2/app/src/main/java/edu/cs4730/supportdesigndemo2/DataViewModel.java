package edu.cs4730.supportdesigndemo2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * this is class to hold data so even when the destroy view is called for the fragment
 * the data can survive.   The left and right fragment are creating their own instance of this object.
 */

public class DataViewModel extends AndroidViewModel {
    private MutableLiveData<String> left;
    private MutableLiveData<String> right;

    public DataViewModel(@NonNull Application application) {
        super(application);
        left = new MutableLiveData<String>("left");
        right = new MutableLiveData<String>("right");
    }

    public LiveData<String> getDataLeft() {
        return left;
    }
    public LiveData<String> getDataRight() {
        return right;
    }

    public String getStrLeft() {
        return left.getValue();
    }
    public String getStrRight() {
        return right.getValue();
    }

    public void setStrLeft(String item) {
        left.setValue(item);
    }

    public void setStrRight(String item) {
        right.setValue(item);
    }

    public void appendStrRight(String item) {
        String prev = right.getValue();
        right.setValue(prev + "\n" + item);
    }

    public void appendStrLeft(String item) {
        String prev = left.getValue();
        left.setValue(prev + "\n" + item);
    }

}
