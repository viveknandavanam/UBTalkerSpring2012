package com.talker.processor;

import com.talker.processor.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;


public class phrase extends ListActivity implements OnInitListener {
	
    private static final int MY_DATA_CHECK_CODE = 0;
	/** Called when the activity is first created. */
    
	private TextToSpeech Tts;
	
    /** Called when the activity is first created. */
    //@Override
	static final String[] PHRASES = new String[] {
    		  "Please read me a story",  "Hello World", "I love you", "How are you doing","Sounds good", "I am fine",
    		  "Where had you been", "I want to go play", "Who are you", "What is your name", "I want to go to the bathroom",
    		  "I am feeling tired", "Where are you going", "I love the jets", "Lets go to the park"
    	  };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.listitem, PHRASES));

		Tts = new TextToSpeech(this, this);
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
            // When clicked, show a toast with the TextView text
            Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                Toast.LENGTH_SHORT).show();
            String speakString = ((TextView) view).getText().toString();
            System.out.println("== "+((TextView) view).getText().toString());
            Tts.speak(speakString, TextToSpeech.QUEUE_FLUSH, null); 
          }
        });
    }
    
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				Tts = new TextToSpeech(this, this);
			} 
			else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}

	}

	
	public void onInit(int status) {		
		if (status == TextToSpeech.SUCCESS) {
			Toast.makeText(phrase.this, 
					"Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
		}
		else if (status == TextToSpeech.ERROR) {
			Toast.makeText(phrase.this, 
					"Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
		}
	}
	
}