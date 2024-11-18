package edu.cs4730.lotsoflists;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.lotsoflists.databinding.ActivityMainBinding;
import edu.cs4730.lotsoflists.databinding.LayoutDialogBinding;

/**
 * complex example of a navigation drawer with recyclerview.  Plus you can add more items
 * to the list and add more categories (ie new lists) as well.
 * <p>
 * The list data is all handled in the ViewModel, ListsViewModel.
 */

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ActionBarDrawerToggle mDrawerToggle;
    myAdapter mAdapter;
    Boolean IsCatInput = false;

    ListsViewModel mViewModel;
    ArrayAdapter<String> catAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        //use the androidx toolbar instead of the default one.
        setSupportActionBar(binding.appBar);

        mViewModel = new ViewModelProvider(this).get(ListsViewModel.class);

        //setup the RecyclerView
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new myAdapter(null, R.layout.my_row, this);  //observer will fix the null
        //add the adapter to the recyclerview
        binding.list.setAdapter(mAdapter);

        mViewModel.getListLD().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> myList) {
                mAdapter.newData(myList);
            }
        });


        //swipe listener for the recycler view
        //ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT |ItemTouchHelper.LEFT) {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                //likely allows to for animations?  or moving items in the view I think.
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //called when it has been animated off the screen.  So item is no longer showing.
                //use ItemtouchHelper.X to find the correct one.
                if (direction == ItemTouchHelper.RIGHT) {
                    //Toast.makeText(getBaseContext(),"Right?", Toast.LENGTH_SHORT).show();
                    int item = viewHolder.getAbsoluteAdapterPosition();  //think this is where in the array it is.
                    //((myAdapter)viewHolder).myName.getText();

                    mViewModel.removeItem(item);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(binding.list);

        binding.list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //yes this code is kind of a mess.   And better I got some of from here:
            //https://github.com/Suleiman19/Android-Material-Design-for-pre-Lollipop/tree/master/MaterialSample/app/src/main/java/com/suleiman/material/activities
            //in the fabhideactivity.java

            int scrollDist = 0;
            private boolean isVisible = true;
            private final float HIDE_THRESHOLD = 100;
            private final float SHOW_THRESHOLD = 50;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //but I want the fab back after the scrolling is done.  Not scroll down a little... that is just stupid.
                    if (!isVisible) {
                        binding.fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2F)).start();
                        //scrollDist = 0;
                        isVisible = true;
                        Log.v("c", "state changed, show be showing....");
                    }

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //so animate to slide down and then and set invisible variables or something.
                //  Check scrolled distance against the minimum
                if (isVisible && scrollDist > HIDE_THRESHOLD) {
                    //  Hide fab & reset scrollDist
                    binding.fab.animate().translationY(binding.fab.getHeight() + getResources().getDimensionPixelSize(R.dimen.fab_margin)).setInterpolator(new AccelerateInterpolator(2F)).start();
                    scrollDist = 0;
                    isVisible = false;
                    Log.v("onScrolled", "maded fab invisible");
                }
                //  -MINIMUM because scrolling up gives - dy values
                else if (!isVisible && scrollDist < -SHOW_THRESHOLD) {
                    //  Show fab & reset scrollDist
                    binding.fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2F)).start();

                    scrollDist = 0;
                    isVisible = true;
                    Log.v("onScrolled", "maded fab visible");
                }

                //  Whether we scroll up or down, calculate scroll distance
                if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
                    scrollDist += dy;
                }
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IsCatInput = false;
                showInputDialog("Input New Data");

            }
        });

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this,  // host activity
                binding.drawerLayout,  //drawerlayout object
                binding.appBar,  //toolbar
                R.string.drawer_open,  //open drawer description  required!
                R.string.drawer_close) {  //closed drawer description

            //called once the drawer has closed.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Categories");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            //called when the drawer is now open.
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        //To disable the icon for the drawer, change this to false
        //mDrawerToggle.setDrawerIndicatorEnabled(true);
        binding.drawerLayout.addDrawerListener(mDrawerToggle);
        //setup the addcat "button"
        binding.addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IsCatInput = true;
                showInputDialog("Add New Category");
            }
        });
        //lastly setup the listview with some simple categories via an array.
        catAdapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, new ArrayList<String>());
        //mViewModel.getAllCat() );
        mViewModel.getCatLD().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                catAdapter.clear();
                catAdapter.addAll(strings);
            }
        });

        binding.leftDrawer.setAdapter(catAdapter);
        binding.leftDrawer.setItemChecked(mViewModel.getlist(), true);  //set the highlight correctly.
        binding.leftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                //yes, this should do something for more interesting.  but this is an example.
                String item = binding.leftDrawer.getAdapter().getItem(position).toString();

                //change the list view to correct one.
                mViewModel.setlist(position);

                // update selected item and title, then close the drawer
                binding.leftDrawer.setItemChecked(position, true);
                //now close the drawer!
                binding.drawerLayout.closeDrawers();
            }
        });

    }

    /**
     * We are going to add data
     * setup a dialog fragment to ask the user for the new item data or category.
     */

    public void showInputDialog(String title) {

        LayoutInflater inflater = LayoutInflater.from(this);
        LayoutDialogBinding binding = LayoutDialogBinding.inflate(inflater);
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme_Dialog));
        builder.setView(binding.getRoot()).setTitle(title);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //add the list and allow the observer to fixes the lists.
                if (IsCatInput) { //add new category
                    mViewModel.addCat(binding.itemAdded.getText().toString());
                } else { //add new data item to list.
                    mViewModel.addItem(binding.itemAdded.getText().toString());
                }
                //Toast.makeText(getBaseContext(), userinput.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
