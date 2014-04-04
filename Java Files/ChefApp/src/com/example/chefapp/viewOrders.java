package com.example.chefapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class viewOrders extends Activity {
	public static List<String> RecvOrders = new ArrayList<String>();
	public static String RecvOrderString;
	public static Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_orders);
	}

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

	
	
	public class TCPReadTimerTask extends TimerTask {
		public void run() {
			ConnectionApplication app = (ConnectionApplication) getApplication();
			if (app.sock != null && app.sock.isConnected()
					&& !app.sock.isClosed()) {

				try {
					InputStream in = app.sock.getInputStream();
					// See if any bytes are available from the Middleman

					int bytes_avail = in.available();
					if (bytes_avail > 0) {

						// If so, read them in and create a sring

					byte buf[] = new byte[bytes_avail];
					in.read(buf);

					final String s = new String(buf, 2, bytes_avail - 2,
								"US-ASCII");

						// As explained in the tutorials, the GUI can not be
						// updated in an asynchronous task. So, update the GUI
						// using the UI thread.

					runOnUiThread(new Runnable() {
						public void run() {
							EditText et = (EditText) findViewById(R.id.RecvdMessage);
							et.setText(s);
						}
					});
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}
