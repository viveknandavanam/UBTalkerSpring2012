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




public class PictureDB extends Activity implements OnInitListener {
    private static final int MY_DATA_CHECK_CODE = 0;
	/** Called when the activity is first created. */
    
	private TextToSpeech Tts;
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.picturedb);

    
    GridView gridview = (GridView) findViewById(R.id.gridview);
    gridview.setAdapter(new ImageAdapter(this));

    gridview.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        	String textToSpeakString="";
        	switch(position)
			{
			case 0:
				textToSpeakString = "banana";
				break;
			case 1:
				textToSpeakString = "blueberry";
				break;
			case 2:
				textToSpeakString = "red grape";
				break;
			case 3:
				textToSpeakString = "jujube";
				break;
			case 4:
				textToSpeakString = "green grape";
				break;
			case 5:
				textToSpeakString = "kiwi";
				break;
			case 6:
				textToSpeakString = "orange";
				break;
			case 7:
				textToSpeakString = "melon";
				break;
			case 8:
				textToSpeakString = "papaya";
				break;
			case 9:
				textToSpeakString = "pear";
				break;
			case 10:
				textToSpeakString = "strawberry";
				break;
			case 11:
				textToSpeakString = "peach";
				break;
			case 12:
				textToSpeakString = "blackberry";
				break;
			case 13:
				textToSpeakString = "cantaloupe";
				break;
			case 14:
				textToSpeakString = "fig";
				break;
			case 15:
				textToSpeakString = "guava";
				break;
			case 16:
				textToSpeakString = "passion fruit";
				break;
			case 17:
				textToSpeakString = "peach";
				break;
			case 18:
				textToSpeakString = "pineapple";
				break;
			case 19:
				textToSpeakString = "plum";
				break;
			case 20:
				textToSpeakString = "raspberries";
				break;
			case 21:
				textToSpeakString = "watermelon";
				break;
			default:
				textToSpeakString = "NULL";
				break;
			}
            Toast.makeText(PictureDB.this, "" + "The Picture "+textToSpeakString+" is Pressed!!", Toast.LENGTH_SHORT).show();
            Tts.speak("I want a "+textToSpeakString, TextToSpeech.QUEUE_FLUSH, null);        
        }
    }); 
    
    
    Intent checkIntent = new Intent();
	checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

    
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
			Toast.makeText(PictureDB.this, 
					"Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
		}
		else if (status == TextToSpeech.ERROR) {
			Toast.makeText(PictureDB.this, 
					"Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
		}
	}
	
	  

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.banana, R.drawable.blueberry,
            R.drawable.grapes_red, R.drawable.cranberries,
            R.drawable.grapes, R.drawable.kiwifruit,
            R.drawable.orange, R.drawable.one_mango,
            R.drawable.papaya, R.drawable.pear1,
            R.drawable.strawberries,R.drawable.apricots,
            R.drawable.blackberry,R.drawable.cantaloupe,
            R.drawable.fig,R.drawable.guavas,
            R.drawable.passion_fruit,R.drawable.peach,
            R.drawable.pineapple,R.drawable.plum,
            R.drawable.raspberries,R.drawable.watermelons
            
           
    };
}
   
         
    
}
        
        
    