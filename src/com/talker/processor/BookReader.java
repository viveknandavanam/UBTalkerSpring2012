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




public class BookReader extends Activity implements OnInitListener {
    private static final int MY_DATA_CHECK_CODE = 0;
	/** Called when the activity is first created. */
    
	private TextToSpeech Tts;
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.bookreader);

    
    GridView gridview = (GridView) findViewById(R.id.gridview);
    gridview.setAdapter(new ImageAdapter(this));

    gridview.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        	String textToSpeakString="";
        	switch(position)
			{
			case 0:
				textToSpeakString = "Humpty Dumpty sat on a wall, Humpty Dumpty had a great fall.All the king's horses and all the king's men, Couldn't put Humpty together again.";
	        	Toast.makeText(BookReader.this, "Humpty Dumpty!!", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				textToSpeakString = "Baa, baa, black sheep, Have you any wool? Yes sir, yes sir, Three bags full. One for the master, One for the dame, And one for the little boy Who lives down the lane.";
	        	Toast.makeText(BookReader.this, "" + "Baa Baa Black Sheep!!", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				textToSpeakString = "One day, three little butterflies were playing. They moved from one flower to another to smell it's beautiful perfume, and then they sleep so they can go tomorrow again.";
	        	Toast.makeText(BookReader.this, "" + "The Three Butterflies!!", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				textToSpeakString = "One day a boy gave his girlfriend twelve roses where eleven were real and one was fake and said, I'll love you until the last rose dies.";
	        	Toast.makeText(BookReader.this, "Love!!", Toast.LENGTH_SHORT).show();
				break;
			case 4:
				textToSpeakString = "Once upon a time there was an abnormal frog called David. He was a prince that was turned into an ugly frog by a spell of a witch. All he wanted was to be normal again. So david went to the witch so that she would turn him back to normal. while he was on his way to the witch's house he saw a beautiful frog named juliet. He felt in love with her. When Juliet saw david she fell in love too. So they kissed and they turned back to being humans. They got married and lived happly ever after.";
	        	Toast.makeText(BookReader.this, "Frog Prince!!", Toast.LENGTH_SHORT).show();
				break;
			case 5:
				textToSpeakString = "Once upon a time there was a girl who loved dogs. she went to the pet store and let all the dogs loose and let them chase her to her house.";
	        	Toast.makeText(BookReader.this, "Girl who loved dogs!!", Toast.LENGTH_SHORT).show();
				break;
			default:
				textToSpeakString = "NULL";
				break;
			}
        	Tts.speak(textToSpeakString, TextToSpeech.QUEUE_FLUSH, null);        
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
			Toast.makeText(BookReader.this, 
					"Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
		}
		else if (status == TextToSpeech.ERROR) {
			Toast.makeText(BookReader.this, 
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
            imageView.setLayoutParams(new GridView.LayoutParams(170, 170));
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
            R.drawable.humptydumpty, R.drawable.baabaablacksheepnurseryrhymes,
            R.drawable.butterflies, R.drawable.boyrosegirl,
            R.drawable.bigkissmeprincessfrogballoon, R.drawable.girldog
           
    };
}
   
         
    
}
        
        
    