package edu.cs4730.lotsoflists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Seker on 1/28/2015.
 *
 * Basically an pretty ugly data structure to hold category names and a list of lists (ie one list for each category).
 *
 * there are Cat (category) and item (list of lists) methods to add, size, plus a setlist which will
 * change which list the data structure returns.
 *
 */
public class morelists {
    int length =0;
    int currentlist = 0;
    ArrayList<String> Categories;
    List<ArrayList<String>> listsoflist;

    morelists() {
        Categories = new ArrayList<String>(Arrays.asList("Phones"));
        listsoflist = new ArrayList<ArrayList<String>>();

        listsoflist.add(new ArrayList<String>(Arrays.asList("Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2")));
        length =1;
        currentlist = 0;
        System.out.print("list is " +listsoflist.get(currentlist).size() );
    }

    public void setlist(int i) {
        currentlist = i;
    }

    public int getsize() {
        return listsoflist.get(currentlist).size();
    }

    public String get(int position) {
        if (position < listsoflist.get(currentlist).size() ) {
            return listsoflist.get(currentlist).get(position);
        } else {
            return "Error!";
        }
    }
    public void addItem(String item) {
        listsoflist.get(currentlist).add(item);
    }

    public void addCat(String item) {
        Categories.add(item);
        //now create the list.
        length++;
        listsoflist.add(new ArrayList<String>());

    }

    public int getcatsize() {
        return Categories.size();
    }

    public String getcat(int position) {
        if (position <Categories.size())
            return Categories.get(position);
        else
            return "Cat error";
    }

    public List<String> getAllCat() {
        return Categories;
    }
}
