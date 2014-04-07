package com.example.chefapp;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class viewOrders extends Activity {
	public static List<String> orders = new ArrayList<String>();
	//public static List<Button> buttons = new ArrayList<Button>();
	public static String stringForOrders;
	public static List<Integer> clientIDList = new ArrayList<Integer>();
	public static Handler mHandler;
	int numA = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_view_orders);
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				//Log.i("UI", "" + stringForOrders);
				viewOrders.orders.add(stringForOrders);
				onResume();
			}
		};
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		Log.i("MY_MESSAGE", "in onResume (ViewOrdersPage)");
		final LinearLayout parentlayout = new LinearLayout(this);
		ScrollView scrollview;
		scrollview = new ScrollView(this);
		parentlayout.setOrientation(LinearLayout.VERTICAL);
		scrollview.addView(parentlayout);
		TextView a,b;
		int ind;
		

		for (ind=0; ind < orders.size();ind++) {
			String s = orders.get(ind).toString();
			int sentinel = s.indexOf('#');
			String str = s.substring(0,sentinel);
			int clientID = Integer.parseInt(str);
			clientIDList.add(clientID); 
			s = s.replace(str+"#","");
			
			final LinearLayout linearHeader = new LinearLayout(this);
			final LinearLayout linear0 = new LinearLayout(this);
			final LinearLayout linear1 = new LinearLayout(this);
			final LinearLayout linearGap = new LinearLayout(this);
			linearHeader.setOrientation(LinearLayout.HORIZONTAL);
			linear0.setOrientation(LinearLayout.HORIZONTAL);
			linear1.setOrientation(LinearLayout.HORIZONTAL);
			linearGap.setOrientation(LinearLayout.HORIZONTAL);
			parentlayout.addView(linearHeader);
			parentlayout.addView(linear0);
			parentlayout.addView(linear1);
			parentlayout.addView(linearGap);
			
			a = new TextView(this);
			

			a.setId(ind);
				a.setText("Client #"+ clientID +":");	
			a.setTextSize(15);
			a.setPadding(18, 13, 18, 13);
			a.setTypeface(null, Typeface.BOLD);
			a.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			linearHeader.addView(a);
			
			
			b = new TextView(this);
			b.setText(s);
			b.setId(50);
			b.setTextSize(15);
			b.setPadding(18, 13, 18, 13);
			b.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
			b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			linear0.addView(b);
			
			Button d = new Button(this);
			d.setText("Done");
			d.setId(ind);
			d.setTextSize(15);
			d.setPadding(8, 3, 8, 3);
			d.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
			d.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			linear1.addView(d);
			
			TextView e = new TextView(this);
			e.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					40));
			linearGap.addView(e);
			
			d.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					parentlayout.removeView(linearHeader);
					parentlayout.removeView(linear0);
					parentlayout.removeView(linear1);
					parentlayout.removeView(linearGap);
					clientIDList.remove(v.getId());
					orders.remove(v.getId());
					sendAlert(clientIDList.get(v.getId()));
				}
			});
			
			this.setContentView(scrollview);

		}
	}
	
	private void sendAlert(int clientID){
		ConnectionApplication app = (ConnectionApplication) getApplication();
		//byte buf[] = new byte[msg.length() + 2];
		byte buf[] = new byte[2];
		buf[0] = (byte)clientID; 
		buf[1] = (byte)5; // 5 means order is completed; 
		

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
