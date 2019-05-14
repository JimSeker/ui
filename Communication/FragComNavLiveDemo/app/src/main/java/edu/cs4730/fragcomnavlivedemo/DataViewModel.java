package edu.cs4730.fragcomnavlivedemo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    private MutableLiveData<Integer> num_one = new MutableLiveData<Integer>();
    private MutableLiveData<Integer> num_two = new MutableLiveData<Integer>();
    private MutableLiveData<String> item = new MutableLiveData<String>();

    //note, no constructor seems to be allowed, or it dies.


    //note, no constructor is allowed, or it dies.  Needs to write a factory interface class, see
    // https://stackoverflow.com/questions/44194579/android-viewmodel-has-no-zero-argument-constructor
    //or I cheated and set it in the "liveData" piece to get the observer.

    LiveData<String> getItemLD() {
        if (item.getValue() == null) item.setValue("default");
        return item;
    }

    String getItem() {
        return item.getValue();
    }

    void setItem(String n) {
        Log.d("VM", "data is " + n);
        item.setValue(n);
    }

    LiveData<Integer> getoneLD() {

        if (num_one.getValue() == null) num_one.setValue(0);
        return num_one;
    }

    int getOne() {

        if (num_one.getValue() != null) {
            return num_one.getValue();
        } else {
            return 0;
        }
    }

    void setOne(int value) {
        Log.d("ViewModel", "one is " + value);
        num_one.setValue(value);
    }

    void incr_One() {
        // if (num_one.getValue() != null)
        num_one.setValue(num_one.getValue() + 1);
    }

    LiveData<Integer> gettwoLD() {
        if (num_two.getValue() == null) num_two.setValue(0);
        return num_two;
    }

    int getTwo() {

        if (num_two.getValue() != null) {
            return num_two.getValue();
        } else {
            return 0;
        }
    }

    void setTwo(int value) {
        Log.d("ViewModel", "two is " + value);
        num_two.setValue(value);
    }

    void incr_Two() {
        //   if (num_two.getValue() != null)
        num_two.setValue(num_two.getValue() + 1);
    }
}
