package com.example.orderapp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SpecialsPage extends Activity {
	public static List<String> specialItems = new ArrayList<String>();
	
	public static String stringForUpdateSpecials;
	public static int counter=0;
	public static Handler mHandler;
	
	public List<String> getSpecialItems() {
		return specialItems;
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_specials_page);
		
		//UI Purpose
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				Log.i("UI",""+stringForUpdateSpecials);
				SpecialsPage.specialItems.add(0, stringForUpdateSpecials);
				onResume();
			}
		};
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.view_menu, menu);
//
//		return true;
//	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("MY_MESSAGE", "in onResume (SpecialsPage)");
		
		ScrollView scrollview;
		scrollview = new ScrollView(this);
		LinearLayout linearlayout = new LinearLayout(this);
		linearlayout.setOrientation(LinearLayout.VERTICAL);
		scrollview.addView(linearlayout);
		
		
		Button d = new Button(this);
		d.setText("Update Specials");
		d.setId(1); 
		d.setTextSize(10);
		d.setPadding(8, 3, 8, 3);
		d.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
		d.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

		linearlayout.addView(d);            
		d.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				onClickRequestUpdateSpecials(v);
			}
		});
		this.setContentView(scrollview);
			
		TextView b;
		
		for(String s: specialItems) {
			LinearLayout linear0 = new LinearLayout(this);
			linear0.setOrientation(LinearLayout.HORIZONTAL);
			linearlayout.addView(linear0);
			b= new TextView(this);
			b.setText(s);
			b.setId(50);
			b.setTextSize(25);
			b.setPadding(18, 13, 18, 13);
			b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
			b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			linear0.addView(b);
			this.setContentView(scrollview);
		}
	}



	//Send empty message to DE2 to get update on specials from SD card.
	public void onClickRequestUpdateSpecials(View view){

		ConnectionApplication app = (ConnectionApplication) getApplication();

		// Create an array of bytes.  First byte will be the
		// message length, and the next ones will be the message

		byte buf[] = new byte[2];
		buf[0] = (byte)app.getChefClientID(); //send to chef client ID but the DE2 will reply, not the chefApp 
		buf[1] = (byte)4; // 4 means request to update specials 
		

		// Now send through the output stream of the socket

		OutputStream out;
		try {
			out = app.sock.getOutputStream();
			try {
				out.write(buf, 0, 2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
