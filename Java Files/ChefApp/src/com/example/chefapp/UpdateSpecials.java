package com.example.chefapp;


import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class UpdateSpecials extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_specials);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_update_specials, menu);
		return true;
	}


	@Override
	protected void onResume() {
		super.onResume();
		Log.i("MY_MESSAGE", "in onResume (MainActivity)");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("MY_MESSAGE", "in onPause (MainActivity)");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("MY_MESSAGE", "in onStop (MainActivity)");
	}

	public void onClickUpdateSpecials(View view){

		ConnectionApplication app = (ConnectionApplication) getApplication();

		// Get the Items and corresponding prices from the edit boxes

		EditText et = (EditText) findViewById(R.id.Item1);
		String Item1 = et.getText().toString();

		et = (EditText) findViewById(R.id.Price1);
		String Price1 = et.getText().toString();
		
		et = (EditText) findViewById(R.id.Item2);
		String Item2 = et.getText().toString();
		
		et = (EditText) findViewById(R.id.Price2);
		String Price2 = et.getText().toString();
		
		String msg =  Item1 + "$" + Price1 + "*" + Item2 + "$" + Price2 + "*";
		// Create an array of bytes.  First byte will be the
		// message length, and the next ones will be the message

		byte buf[] = new byte[msg.length() + 2];
		buf[0] = (byte)255; 
		buf[1] = (byte)3; // 3 means update specials 
		System.arraycopy(msg.getBytes(), 0, buf, 2, msg.length());

		// Now send through the output stream of the socket

		OutputStream out;
		try {
			out = app.sock.getOutputStream();
			try {
				out.write(buf, 0, msg.length() + 2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

