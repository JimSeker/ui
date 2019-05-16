package edu.cs4730.fragcomnavvmodeldemo;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    int num_one = 0;
    int num_two = 0;
    private String item = "default";

    //note, no constructor seems to be allowed, or it dies.


    String getItem() {
        return item;
    }

    void setItem(String n) {
        Log.d("VM", "data is " + n);
        item = n;
    }


}
