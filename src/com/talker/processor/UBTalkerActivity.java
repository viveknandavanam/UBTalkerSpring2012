package com.talker.processor;

import com.talker.processor.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class UBTalkerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

      //----On Click of a radio button coresponding to Admin and User-------  	  
  	  
    	  RadioButton r1= (RadioButton) findViewById(R.id.radioButton1);
             r1.setOnClickListener(new View.OnClickListener() {
             public void onClick(View arg0) {
             Intent i1 = new Intent(UBTalkerActivity.this, TaskSelectionAdmin.class);
             startActivity(i1);
             }
          });
                          
             RadioButton r2= (RadioButton) findViewById(R.id.radioButton2);
             r2.setOnClickListener(new View.OnClickListener() {
             public void onClick(View arg0) {
             Intent i2 = new Intent(UBTalkerActivity.this, TaskSelection.class);
             startActivity(i2);
             }
          });  
    }
}