package com.example.picture;

import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.app.Activity;

public class DrawONpicture extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw_onpicture);
		byte[] byteArray = (byte[]) getIntent().getSerializableExtra("image");
		Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
		((ImageView) findViewById(R.id.drawImage1)).setImageBitmap(bm);
	}
}
