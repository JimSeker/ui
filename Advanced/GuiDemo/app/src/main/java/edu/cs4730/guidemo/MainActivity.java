package edu.cs4730.guidemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import edu.cs4730.guidemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    ActivityMainBinding binding;

    private ActionBarDrawerToggle mDrawerToggle;
    FragmentManager fragmentManager;

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
        fragmentManager = getSupportFragmentManager();

        //first setup the listview with some simple categories via an array.
        String[] values = getResources().getStringArray(R.array.sections);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, values);

        binding.leftDrawer.setAdapter(adapter);
        binding.leftDrawer.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                //based on click, change to correct fragment.
                String item = binding.leftDrawer.getAdapter().getItem(position).toString();
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
                        break;
                }
                // update selected item and title, then close the drawer
                binding.leftDrawer.setItemChecked(position, true);
                //now close the drawer!
                binding.drawerLayout.closeDrawer(binding.leftDrawer);
            }
        });

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                binding.drawerLayout, /* DrawerLayout object */
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
        binding.drawerLayout.addDrawerListener(mDrawerToggle);
        //first instance, so the default is zero.
        fragmentManager.beginTransaction().replace(R.id.container, new Text_Fragment()).commit();
        binding.leftDrawer.setItemChecked(0, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
