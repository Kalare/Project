package com.example.picture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	private Bitmap bm;
	private URI path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.makePicture).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takePictureIntent, 1);
				}
			}
		});

		findViewById(R.id.drawPicture).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, DrawONpicture.class);
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();
				intent.putExtra("image", byteArray);
				startActivity(intent);
			}
		});

		findViewById(R.id.sendPicture).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SENDTO);	                
				intent.setData(Uri.parse("mailto: "));
				intent.putExtra(Intent.EXTRA_STREAM, path);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK){
			bm = (Bitmap) data.getExtras().get("data");
			saveImageToInternalStorage(bm);
		} else {
			Toast.makeText(getApplicationContext(), "Zapisywanie siê nie powiod³o", Toast.LENGTH_SHORT).show();
		}
	}

	public boolean saveImageToInternalStorage(Bitmap image) {
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/saved_images");
		myDir.mkdirs();
		File file = new File(myDir, "Image.jpg");
		if (file.exists())
			file.delete();
		try {
			FileOutputStream out = new FileOutputStream(file);
			image.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			path = new URI(root+"/saved_images/Image.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
