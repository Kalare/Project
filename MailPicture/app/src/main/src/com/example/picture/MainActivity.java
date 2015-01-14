package com.example.picture;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	
	 static final int REQUEST_IMAGE_CAPTURE = 1;
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	        findViewById(R.id.makePicture).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	                }
	            }
	        });
	        
	        findViewById(R.id.drawPicture).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					drawOnpicture();
					
				}
			});
	        		
	        findViewById(R.id.sendPicture).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	
	                Intent intent = new Intent(Intent.ACTION_SENDTO);	                
	                intent.setData(Uri.parse("mailto: "));
	                startActivity(intent);
	            }
	        });

	    }
	    
	    private void drawOnpicture(){
	    	Intent intent = new Intent(this, DrawONpicture.class);
			startActivity(intent);
	    }
	}
