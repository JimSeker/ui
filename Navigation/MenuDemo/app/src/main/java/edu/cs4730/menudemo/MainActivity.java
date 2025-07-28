package edu.cs4730.menudemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import edu.cs4730.menudemo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

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
        binding.popup.setOnClickListener( new OnClickListener(){
            @Override
            public void onClick(View v) {
                showPopupMenu(v); //this is to the code below, not an API call.
            }
        });
    }

    private void showPopupMenu(View v){
        PopupMenu popupM = new PopupMenu(this, v); //note "this" is the activity context, if you are using this in a fragment.  using getActivity()
        popupM.inflate(R.menu.popup);
        popupM.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
        popupM.show();
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

        if (id == R.id.menuandfragdemo) {
            startActivity(new Intent(MainActivity.this, FragMenuActivity.class));
            return true;
        }else if (id == R.id.actbaritemsdemo) {
            startActivity(new Intent(MainActivity.this, ActionMenuActivity.class));
            return true;
        }else if (id == R.id.viewpagerbuttondemo) {
            startActivity(new Intent(MainActivity.this, ViewPagerButtonMenuActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
