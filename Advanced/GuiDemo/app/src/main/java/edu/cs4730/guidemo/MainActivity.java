package edu.cs4730.guidemo;


import android.content.res.Configuration;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        fragmentManager = getSupportFragmentManager();

        //first setup the listview with some simple categories via an array.
        String[] values = getResources().getStringArray(R.array.sections);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, values);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                //based on click, change to correct fragment.
                String item = mDrawerList.getAdapter().getItem(position).toString();
                switch (position) {
                    case 0:
                        fragmentManager.beginTransaction().replace(R.id.container, new Text_Fragment()).commit();
                        break;
                    case 1:
                        fragmentManager.beginTransaction().replace(R.id.container, new Input_Fragment()).commit();
                        break;
                    case 2:
                        fragmentManager.beginTransaction().replace(R.id.container, new Image_Fragment()).commit();
                        break;
                    case 3:
                        fragmentManager.beginTransaction().replace(R.id.container, new Button_Fragment()).commit();
                        break;
                    case 4:
                        fragmentManager.beginTransaction().replace(R.id.container, new ButtonCL_Fragment()).commit();
                        break;
                    case 5:
                        fragmentManager.beginTransaction().replace(R.id.container, new Relativelayout_Fragment()).commit();
                        break;
                    case 6:
                        fragmentManager.beginTransaction().replace(R.id.container, new RadioCheck_Fragment()).commit();
                        break;
                    case 7:
                        fragmentManager.beginTransaction().replace(R.id.container, new Spinner_Fragment()).commit();
                        break;
                    case 8:
                        fragmentManager.beginTransaction().replace(R.id.container, new ViewSwitch_Fragment()).commit();
                        break;
                    case 9:
                        fragmentManager.beginTransaction().replace(R.id.container, new Picker_Fragment()).commit();
                        break;                }
                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                //now close the drawer!
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });


        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.string.drawer_open, /* "open drawer" description for accessibility */
                R.string.drawer_close /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Categories");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //first instance, so the default is zero.
        fragmentManager.beginTransaction().replace(R.id.container, new Text_Fragment()).commit();
        mDrawerList.setItemChecked(0, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
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


    //needed to make the nav drawer draw correctly.
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
