package edu.cs4730.botnavguidemo;

import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.cs4730.botnavguidemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements BottomNavigationDialogFragment.OnFragmentInteractionListener {
    String TAG = "MainActivity";
    FragmentManager fragmentManager;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.bottomAppBar);  //this set the bottom bar to the the Action Bar.
        binding.bottomAppBar.setTitle(R.string.title_home);
        fragmentManager = getSupportFragmentManager();

        binding.bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "AppBar Navigation clicked.");
                BottomSheetDialogFragment bottomSheetDialogFragment = new BottomNavigationDialogFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });

        fragmentManager = getSupportFragmentManager();

        binding.fab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                Log.d(TAG, "Refresh FAB clicked.");

            }
        });

        fragmentManager.beginTransaction().replace(R.id.container, new Text_Fragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.navigation_home) {

            return true;
        } else if (id == R.id.navigation_dashboard) {

            return true;
        } else if (id == R.id.navigation_notifications) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentPicker(int id) {
        switch (id) {
            case R.id.m_text:
                fragmentManager.beginTransaction().replace(R.id.container, new Text_Fragment()).commit();
                break;
            case R.id.m_tex_input:
                fragmentManager.beginTransaction().replace(R.id.container, new Input_Fragment()).commit();
                break;
            case R.id.m_itemimages:
                fragmentManager.beginTransaction().replace(R.id.container, new Image_Fragment()).commit();
                break;
            case R.id.m_buttons:
                fragmentManager.beginTransaction().replace(R.id.container, new Button_Fragment()).commit();
                break;
            case R.id.m_itembuttons:
                fragmentManager.beginTransaction().replace(R.id.container, new ButtonCL_Fragment()).commit();
                break;
            case R.id.m_relative:
                fragmentManager.beginTransaction().replace(R.id.container, new Relativelayout_Fragment()).commit();
                break;
            case R.id.m_radio:
                fragmentManager.beginTransaction().replace(R.id.container, new RadioCheck_Fragment()).commit();
                break;
            case R.id.m_spinners:
                fragmentManager.beginTransaction().replace(R.id.container, new Spinner_Fragment()).commit();
                break;
            case R.id.m_itemviewswitcher:
                fragmentManager.beginTransaction().replace(R.id.container, new ViewSwitch_Fragment()).commit();
                break;
            case R.id.m_dialogpickers:
                fragmentManager.beginTransaction().replace(R.id.container, new Picker_Fragment()).commit();
                break;
        }
    }
}
