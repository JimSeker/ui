Android material Design Examples
==============

<b>SimpleDemo</b> is basic example of using Material design.  There is no real java code here, it's all in the values/color and styles.xml files (values and values-v21).  There are also two screenshots device-4.4.png and device-5.0.png to show how style looks.

<b>ToolbarDemo</b> uses the v7.toolbar and style pieces.  see the app_bar.xml and activity_mail.xml, plus main_activity.java for the bulk of the code.  Again device-4.4.png and device-5.0.png shows the design.

<b>navDrawerDemo</b> is Navigation bar demo with the colors and toolbarnavDrawerDemo is the same example, but uses the toolbar.  See pictures in the directories for the differences.

<b>CirToolbarNavDrawerDemo</b> uses toolbarnavDrawerDemo and uses the suppor design floating action button.  Previous versions used adds the Floating Action button (using a library from https://github.com/makovkastar/FloatingActionButton) 
A useful guide to added libraries (see Studio 1.0 answer) http://stackoverflow.com/questions/16588064/how-do-i-add-a-library-project-to-the-android-studio A somewhat helpful visual guide to adding libraries: http://youtu.be/1MyBO9z7ojk 

<b>talltoolbarDemo</b> is just replicating googles material Design with an extended toolbar as an example.  This example is no longer needed with the support design library.  

<b>RecyclerViewDemo</b> is a very simple example of how to use a RecyclerView and CardView (via support.v7).   Note a RecyclerView is a more powerful listview, which was introduced into android 5 and the support.v7 library.

<b>RecyclerViewDemo2</b> is more complex version using RecyclerView and CardView (support.v7). This example uses the phonelist from the listview example as the data set.

<b>RecyclerViewDemo3</b> is a rewrite of the listdemo from the listview using RecyclerView and CardView (support.v7). It has everything in fragments and uses a viewpager to switch between the examples.

<b>RecyclerViewSwipeRefresh</b> based on RecyclerViewDemo, but adds a swipeRefreashLayout so the user can trigger a refresh of data.

<b>SupportDesignDemo</b> is demo of some of the support Design widgets.  Floating action Button, inputtextlayout, snack bar, and using the snackbar and floating action button together (hint the fab moves out of the way).

<b>SupportDesignDemo2</b> uses the support design tablayout with a viewpager.  that said, The stripetab with the viewpager is much easier to use then the tablayout.

<b>SupportDesign3</b> show how to use the larger toolbar and how to collapse it as well.

<b>LotsofLists</b> is a complex demo using a Navigation bar and RecyclerView to display several different lists, plus you can add more items the lists and add more lists to the navigation as well.

These are example code for University of Wyoming, Cosc 4730 Mobile Programming course.  All examples are for Android.
