package com.example.chefapp;


import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

public class UpdateSpecials extends Activity {
	public static String[] specials= new String[2];
	Button btnClosePopup;
	PopupWindow popupMessage;

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_view_orders, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, Connection.class);
			startActivity(intent);
			return true;
		case R.id.action_updateSpecials:
			Intent intent2 = new Intent(this, UpdateSpecials.class);
			startActivity(intent2);
			return true;
		case R.id.action_viewOrders:
			Intent intent3 = new Intent(this, viewOrders.class);
			startActivity(intent3);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_specials);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu_update_specials, menu);
//		return true;
//	}


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
		
		
		
		specials[0]= Item1;
		
		

		et = (EditText) findViewById(R.id.Price1);
		String Price1 = et.getText().toString();
		
		
		et = (EditText) findViewById(R.id.Item2);
		String Item2 = et.getText().toString();
		
		
		
		specials[1]= Item2;
		
		et = (EditText) findViewById(R.id.Price2);
		String Price2 = et.getText().toString();
		
		if(Price2.equals("")|| Price1.equals("") || Item1.equals("") || Item2.equals(""))
			initiatePopupWindow();
		
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
	
	private PopupWindow pwindo;
	private void initiatePopupWindow() { 
		try { 

			//MediaPlayer mp = MediaPlayer.create(this, R.raw.slayer);  
			//mp.start();


			// We need to get the instance of the LayoutInflater 
			LayoutInflater inflater = (LayoutInflater) UpdateSpecials.this 
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			View layout = inflater.inflate(R.layout.popup,(ViewGroup)

					findViewById(R.id.popup_element)); 
			pwindo = new PopupWindow(layout, 450, 450, true); 
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

			
			btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup); 
			btnClosePopup.setOnClickListener(cancel_button_click_listener);

			btnClosePopup.setTextColor(Color.RED);


		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}

	private OnClickListener cancel_button_click_listener = new OnClickListener() { 
		public void onClick(View v) { 
			pwindo.dismiss();

		}

	};
}

