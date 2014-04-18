package com.example.orderapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Waiting extends Activity {
	public static Handler mHandler;
	public static int sig = 0;
	Button btnClosePopup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiting);
		final TextView textViewToChange = (TextView) findViewById(R.id.textView1dd);
		
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
			
				while(sig != 9)
				{
				}
				if(sig == 9)
				{
					initiatePopupWindow();
					textViewToChange.setText("Order for your table is done, come get it at the kitchen!");	
				}
			}
		};	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.waiting, menu);
		return true;
	}
	
	private PopupWindow pwindo;
	private void initiatePopupWindow() { 
		try { 
			
			
		// We need to get the instance of the LayoutInflater 
		LayoutInflater inflater = (LayoutInflater) Waiting.this 
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View layout = inflater.inflate(R.layout.otherpopup,(ViewGroup)

		findViewById(R.id.popup_element)); 
		pwindo = new PopupWindow(layout, 450, 450, true); 
		pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

		btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup); 
		btnClosePopup.setOnClickListener(cancel_button_click_listener);

		btnClosePopup.setTextColor(Color.BLUE);
		
		
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

