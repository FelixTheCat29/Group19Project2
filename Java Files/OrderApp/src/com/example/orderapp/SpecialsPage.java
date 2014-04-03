package com.example.orderapp;

import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SpecialsPage extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specials_page);
		TextView textViewToChange = (TextView) findViewById(R.id.text111);
		textViewToChange.setText("Currently ordering for guest "+NumberCustomers.getSelCust()+":");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_menu, menu);

		return true;
	}


	//Send empty message to DE2 to get update on specials from SD card.
	public void onClickRequestUpdateSpecials(View view){

		ConnectionApplication app = (ConnectionApplication) getApplication();

		// Create an array of bytes.  First byte will be the
		// message length, and the next ones will be the message

		byte buf[] = new byte[2];
		buf[0] = (byte)255; 
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
