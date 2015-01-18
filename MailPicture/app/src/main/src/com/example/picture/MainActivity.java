package com.example.picture;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	 Bitmap bp;
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
            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if (resultCode == RESULT_OK){
                    bp = (Bitmap) data.getExtras().get("data");
                }else{
                    Toast.makeText(getApplicationContext(), "Nie zapisano zdjęcia", Toast.LENGTH_SHORT).show();
                }

            }
	        
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
