package com.example.chefapp;

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
	public static int counter = 0;
	public static Handler mHandler;

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
		TextView b;
		int ind;

		for (ind=0; ind < orders.size();ind++) {
			String s = orders.get(ind).toString();
			final LinearLayout linear0 = new LinearLayout(this);
			//LinearLayout linear1 = new LinearLayout(this);
			linear0.setOrientation(LinearLayout.HORIZONTAL);
			parentlayout.addView(linear0);
			b = new TextView(this);
			b.setText(s);
			b.setId(50);
			b.setTextSize(15);
			b.setPadding(18, 13, 18, 13);
			b.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
			b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			linear0.addView(b);
			
			//LinearLayout linear1 = new LinearLayout(this);
			//linear1.setOrientation(LinearLayout.VERTICAL);
			//linearlayout.addView(linear1);
			Button d = new Button(this);
			d.setText("Done");
			d.setId(ind);
			d.setTextSize(15);
			d.setPadding(8, 3, 8, 3);
			d.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
			d.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			linear0.addView(d);
			
			d.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					parentlayout.removeView(linear0);
					orders.remove(v.getId());
				}
			});
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
