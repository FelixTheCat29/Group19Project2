package com.example.chefapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class viewOrders extends Activity {
	public static List<String> orders = new ArrayList<String>();
	
	public static String stringForOrders = "";
	public static int counter=0;
	public static Handler mHandler;
	
	public List<String> getOrders() {
		return orders;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_view_orders);
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				Log.i("UI",""+stringForOrders);
				viewOrders.orders.add( stringForOrders);
				onResume();
			}
		};
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("MY_MESSAGE", "in onResume (ViewOrdersPage)");
		
		ScrollView scrollview;
		scrollview = new ScrollView(this);
		LinearLayout linearlayout = new LinearLayout(this);
		linearlayout.setOrientation(LinearLayout.VERTICAL);
		scrollview.addView(linearlayout);
			
		TextView b;
		
		for(String s: orders) {
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

}
