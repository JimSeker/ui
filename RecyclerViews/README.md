Android RecyclerView Examples
==============

`RecyclerViewDemo` (java) is a very simple example of how to use a RecyclerView and CardView. 

`RecyclerViewDemo_kt` (kotlin) is a very simple example of how to use a RecyclerView and CardView.   

`RecyclerViewDemo2` (java) is more complex version using RecyclerView and CardView. This example uses the phonelist from the listview example as the data set.

`RecyclerViewDemo2_kt` (kotlin) is more complex version using RecyclerView and CardView. This example uses the phonelist from the listview example as the data set.

`RecyclerViewDemo3` (java) is a rewrite of the listdemo from the listview using RecyclerView and CardView. It has everything in fragments and uses a viewpager to switch between the examples.

`RecyclerViewDemo3_kt` (kotlin) is a rewrite of the listdemo from the listview using RecyclerView and CardView. It has everything in fragments and uses a viewpager to switch between the examples.

`RecyclerViewSwipeRefresh` (java) based on RecyclerViewDemo, but adds a swipeRefreashLayout so the user can trigger a refresh of data.

`RecyclerViewSwipeRefresh_kt` (kotlin) based on RecyclerViewDemo, but adds a swipeRefreashLayout so the user can trigger a refresh of data.

`LotsofLists` (java) is a complex demo using a Navigation bar and RecyclerView to display several different lists, plus you can add more items the lists and add more lists to the navigation as well.  The data is all stored in a ViewModel and observers are used the update the lists.

`LotsofLists_kt` (kotlin) is a complex demo using a Navigation bar and RecyclerView to display several different lists, plus you can add more items the lists and add more lists to the navigation as well.  The data is all stored in a ViewModel and observers are used the update the lists.

`CallBacksDemo` (java) uses a RecyclerView and shows how to setup a listeners and callbacks to pass data from the recyclerview adapter to the MainActivity (via a fragment).

`CallBacksDemo` (kotlin) uses a RecyclerView and shows how to setup a listeners and callbacks to pass data from the recyclerview adapter to the MainActivity (via a fragment). Kotlin actually seems to make the callbacks even more of a mess.  I recommend a modelview, instead of callbacks.

`CallBacksItemViewDemo` (java) uses a RecyclerView and sets each itemview to have a listener, so a user can click on the item in the recyclerview.  This uses a set of callbacks to get the data back to main activity (via a fragment) via the adapter.  

`CallBacksItemViewDemo_kt` (kotlin) uses a RecyclerView and sets each itemview to have a listener, so a user can click on the item in the recyclerview.  This uses a set of callbacks to get the data back to main activity (via a fragment) via the adapter.  again, modelview is easier.

`ModelViewRecyeclerViewDemo` (java) uses a recyclerview and instead of all the callbacks like in CallBackDemo, it uses a ModelView with LiveData to trigger observers in the Fragment and Activity to pass data back from the recyclerview adapter.

`ModelViewRecyeclerViewDemo_kt` (kotlin) uses a recyclerview and instead of all the callbacks like in CallBackDemo, it uses a ModelView with LiveData to trigger observers in the Fragment and Activity to pass data back from the recyclerview adapter.

---

These are example code for University of Wyoming, Cosc 4730 Mobile Programming course and cosc 4735 Advance Mobile Programing course. 
All examples are for Android.
