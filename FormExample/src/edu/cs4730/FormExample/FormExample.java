package edu.cs4730.FormExample;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class FormExample extends Activity implements RadioGroup.OnCheckedChangeListener, TextWatcher, 
            Button.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener {
    /** Called when the activity is first created. */
	RadioGroup sounds;
	EditText et; 
	ProgressBar pbok;
	SeekBar pb;
    Button btnalert, btnok, btnDate;
    Dialog dialog ;
    TextView tvok, label, TVdate, TVspinner;
    String[] items = {"Jim", "Allyson", "Ruben", "Jeff", "Jerry", "Kim", "John"};
    Calendar dateAndTime = Calendar.getInstance();
    DateFormat fmtDateAndTime=DateFormat.getDateInstance();
    DatePickerDialog.OnDateSetListener d;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        et = (EditText) findViewById(R.id.ETname);
        et.addTextChangedListener(this);
        label = (TextView) findViewById(R.id.Label01);
 //       label.setText("WTF");  But this one works fine!
        sounds = (RadioGroup)findViewById(R.id.SndGroup);
        sounds.setOnCheckedChangeListener(this);
        
        pb = (SeekBar) findViewById(R.id.ProgressBar01);
        pb.setMax(10);
        pb.setOnClickListener(this);
        btnalert = (Button) findViewById(R.id.Button01);
        btnalert.setOnClickListener(this);
        //dialog information
        dialog = new  Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Dialog");
        btnok = (Button) dialog.findViewById(R.id.diaglogBTN);
        btnok.setOnClickListener(new OKListener(dialog));
        pbok = (ProgressBar) dialog.findViewById(R.id.dialogPB);
        pbok.setMax(100);
        tvok = (TextView) dialog.findViewById(R.id.dialogTV);       
        
        //back to main layout stuff
        Spinner s = (Spinner)findViewById(R.id.Spinner01);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);
        TVspinner = (TextView)findViewById(R.id.TVspinner);
        TVdate = (TextView)findViewById(R.id.TVdate);
        btnDate = (Button)findViewById(R.id.Button02);
        btnDate.setOnClickListener(this);
        //Date picker stuff, instead of implementing a listener, this time just declare one!
        d = new DatePickerDialog.OnDateSetListener() {
        	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        		dateAndTime.set(Calendar.YEAR, year);
        		dateAndTime.set(Calendar.MONTH, monthOfYear);
        		dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        		TVdate.setText(fmtDateAndTime.format(dateAndTime.getTime()));
        	}
        };
    }
	
    /*  Radio group listener for OnCheckedChangeListener */
    public void onCheckedChanged(RadioGroup group, int CheckedId) {
    	if (group == sounds) { //if not sounds, we are in trouble!
    		if (CheckedId == R.id.RB01) {
    			// information radio button clicked
    		} else if (CheckedId == R.id.RB02) {
    			// Confirmation radio button clicked
    		} else if (CheckedId == R.id.RB03) {
    			// Warning radio button clicked
    		}
    	}
    }
    /* EditView listeners */
    public void onTextChanged (CharSequence s, int start, int before, int count) {
    	if (et.length() >10) {
    		pb.setProgress(10);
    	} else {
    		pb.setProgress(et.length());
    	}
    }
    public void beforeTextChanged( CharSequence s, int start, int count, int after) {
    	
    }
    public void afterTextChanged(Editable s) {
    	
    }
    /* button listener */
    public void onClick(View v) {
    	if (v == btnalert) {
    		dialog.show(); 	
    		new Thread(new GaugeUpdater()).start();
    	}  else if (v == btnDate) {
    		new DatePickerDialog(FormExample.this, d,
    	      dateAndTime.get(Calendar.YEAR),
    	      dateAndTime.get(Calendar.MONTH),
    	      dateAndTime.get(Calendar.DAY_OF_MONTH)
    		).show();
    				
    	}
    }
    //seek Bar override methods
    public void  onStartTrackingTouch  (SeekBar  seekBar) {
    	//don't care
    }
    public void  onStopTrackingTouch  (SeekBar  seekBar) {
    	//don't care
    }
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    	//uh... couldn't figure out something fun to do.
    }
    
    //spinner listener
    public  void onItemSelected(AdapterView<?>  parent, View  view, int position, long id) {
    	TVspinner.setText(items[position]);	
      }
    
    public  void onNothingSelected(AdapterView<?> parent) {
      	TVspinner.setText("What you didn't select anything?");
      }
    
    //another option for listeners. 
    protected class OKListener implements OnClickListener {

         private Dialog dialog;

         
         public OKListener(Dialog dialog) {
              this.dialog = dialog;
         }

         public void onClick(View v) {
              dialog.dismiss();
         }
    } 
    
    private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
            	tvok.setText("Process done");  //Causes a damn exception, why?!!?? WTF?
            }

    };
    
    class GaugeUpdater implements Runnable {  //this is a thread to update Progress Bar.
        TextView tvokd;
    	public void run() {
          try {
        	  pbok.setProgress(0);
            while (pbok.getProgress() < pbok.getMax()) {
              Thread.sleep(1000);//1 second
              pbok.setProgress(pbok.getProgress() + 10);
            } 
            if (tvok != null ) {
            	String done = new String ("Done.");
            	handler.sendEmptyMessage(0);
            	// tvok.setText(done);  Causes a damn exception, why?!!?? WTF?
             } else {
            	pbok.setProgress(0);
            }
          } catch (InterruptedException Error) {
            throw new RuntimeException(Error.getMessage());
          }
        }
      }
}  
