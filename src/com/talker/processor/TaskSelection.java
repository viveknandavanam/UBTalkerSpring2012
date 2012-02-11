package com.talker.processor;

import com.talker.processor.R;
import com.talker.mail.EmergencyEmail;
import com.talker.mail.GmailSender;
import com.talker.mail.Mail;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;




public class TaskSelection extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState);
    	  setContentView(R.layout.taskselection);
    	  
    	  //------On Clicking the Pictures Button---------------

    	  Button b2= (Button) findViewById(R.id.button1);
    	  
    	  
             b2.setOnClickListener(new View.OnClickListener() {
             public void onClick(View arg0) {
             Intent i1 = new Intent(TaskSelection.this, PictureDB.class);
             startActivity(i1);
             }
          });

          	  //------On Clicking the Pictures Button---------------

          	  Button b4= (Button) findViewById(R.id.Button01);
          	  
                   b4.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View arg0) {
                   Intent i1 = new Intent(TaskSelection.this, phrase.class);
                   startActivity(i1);
                   	
                   }
                });
             
       	  //------On Clicking the Pictures Button---------------

       	  Button b3= (Button) findViewById(R.id.Button03);
       	  
                b3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                Intent i1 = new Intent(TaskSelection.this, BookReader.class);
                startActivity(i1);
                	
                }
             });
    	  
    	  
    	  //------On Clicking the Emergency Email Button---------
    	  
    	  Button b1= (Button) findViewById(R.id.Button04);
          b1.setOnClickListener(new View.OnClickListener() {
          public void onClick(View arg0) {
          
        	  AlertDialog.Builder builder = new AlertDialog.Builder(TaskSelection.this);
        	  
//        	  EmergencyEmail e1=new EmergencyEmail();
//        	  e1.sendEmergencyEmail();
//        	  Mail e2=new Mail();
//        	  try {
//				System.out.println("Mail was sent: "+e2.send());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

        	System.out.println("---->");
            GmailSender sender = new GmailSender("chatterbox.ub@gmail.com", "talkwithme");
        	System.out.println("---->");
            try {
				sender.sendMail("This is Subject",   
				        "This is Body",   
				        "chatterbox.ub@gmail.com",   
				        "vivek.nandavanam@gmail.com,viveknan@buffalo.edu");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        	  builder.setMessage("Emergency Emails are sent to following contacts:")
        	         .setCancelable(false)
        	         .setPositiveButton("Ok", null);
        	  builder.show();
        	  
        	  
        	  
        	  

          }
       });
    	  
    	  
    	  
	}


}