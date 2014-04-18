package com.example.chefapp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	// public static List<Button> buttons = new ArrayList<Button>();
	public static String stringForOrders;
	public static List<Integer> clientIDList = new ArrayList<Integer>();
	public static Handler mHandler;
	public static List<String> customerOrder = new ArrayList<String>();
	public static List<int[][]> eachOrder = new ArrayList<int[][]>();

	public static Map<Integer, List<String>> specialItemsMap = new HashMap<Integer, List<String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_view_orders);
		mHandler = new Handler() {
			public void handleMessage(Message msg) {
				Log.i("UI", "" + stringForOrders);
				// int sentinel = stringForOrders.indexOf('#');
				// String str = stringForOrders.substring(0, sentinel);
				// int clientID = Integer.parseInt(str);
				// clientIDList.add(clientID);
				// stringForOrders = stringForOrders.replace(str + "#", "");
				viewOrders.orders.add(stringForOrders);

				String[] orderFor1Guest = stringForOrders.split("\\*");
				Log.i("UI", "" + orderFor1Guest[0]);
				// for (int i = 0; i < parts.length; i++) {
				// Log.i("UI", "" + parts[i]);
				// customerOrder.add(parts[i]);
				// }

				int oneTable[][] = new int[orderFor1Guest.length][13];
				for (int i = 0; i < orderFor1Guest.length; i++) {
					String splitOrder[] = orderFor1Guest[i].split("\\/");
					Log.i("UI", "" + splitOrder[i]);
					// int code[] = new int[splitOrder.length];
					for (int j = 0; j < splitOrder.length; j++) {
						oneTable[i][j] = Integer.parseInt(splitOrder[j]);
						Log.i("UI", "" + oneTable[i][j]);
					}
					// Log.i("UI", "" + eachOrder.get(i));
					// Log.i("UI", "" + parts[i]);
					// viewOrders.customerOrder.add(parts[i].split("\\/"));
					// Customer is an array of array of strings
					// Elements of Customer are String[]
				}
				eachOrder.add(oneTable);
				List<String> temp = new ArrayList<String>();
				temp.add(UpdateSpecials.specials[0]);
				temp.add(UpdateSpecials.specials[1]);
				specialItemsMap.put(eachOrder.size() - 1, temp);

				onResume();
			}
		};
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("in onresume", "OnResume");
		ScrollView scrollview;
		scrollview = new ScrollView(this);
		final LinearLayout linearlayout = new LinearLayout(this);
		linearlayout.setOrientation(LinearLayout.VERTICAL);
		scrollview.addView(linearlayout);
		TextView b;
		TextView a;
		TextView c;

		// String[][] Customer = new String[][]{};

		// String[] parts = stringForOrders.split("\\*");
		// int numberCustomers = parts.length;
		// for(int i=0; i<numberCustomers; i++)
		// {
		// Customer[i] = parts[i].split("\\/");
		//
		// //Customer is an array of array of strings
		// //Elements of Customer are String[]
		//
		// }

		String[] menuItems = { "Kokanee", "Chivas", "Budweiser", "Ballantines",
				"Pizza", "Hamburger", "Sandwich", "French Fries", "Edamame",
				"Salad", "Cheese", "", "" };
		menuItems[11] = UpdateSpecials.specials[0];
		menuItems[12] = UpdateSpecials.specials[1];
		String special1 = menuItems[11];
		String special2 = menuItems[12];
		// When chef name 11 and special2, save them here

		for (int ind = 0; ind < orders.size(); ind++) {
			Log.i("OnResume", "in orders loop" + ind);
			final LinearLayout linearHeader = new LinearLayout(this);
			final LinearLayout linear0 = new LinearLayout(this);
			final LinearLayout linearGap = new LinearLayout(this);
			final LinearLayout linear2 = new LinearLayout(this);
			linearHeader.setOrientation(LinearLayout.HORIZONTAL);
			linear0.setOrientation(LinearLayout.HORIZONTAL);
			linear2.setOrientation(LinearLayout.HORIZONTAL);
			linearGap.setOrientation(LinearLayout.HORIZONTAL);
			linearlayout.addView(linearHeader);
			linearlayout.addView(linear0);
			c = new TextView(this);
			c.setId(ind);
			c.setText("Client #: " + clientIDList.get(ind));
			c.setTextSize(15);
			c.setPadding(18, 13, 18, 13);
			c.setTypeface(null, Typeface.BOLD);
			c.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			linearHeader.addView(c);

			for (int j = 0; j < eachOrder.get(ind).length; j++) {
				Log.i("OnResume", "in eachOrder.get(ind) loop" + j);

				LinearLayout sublayout = new LinearLayout(this);
				sublayout.setOrientation(LinearLayout.VERTICAL);
				linear0.addView(sublayout);
				LinearLayout sublayout_0 = new LinearLayout(this);
				sublayout_0.setOrientation(LinearLayout.HORIZONTAL);
				sublayout.addView(sublayout_0);

				a = new TextView(this);
				a.setText("	Guest" + (j + 1) + ":");
				a.setId(50);
				a.setTextSize(15);
				a.setPadding(18, 13, 18, 13);
				a.setTypeface(null, Typeface.BOLD);
				a.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				sublayout_0.addView(a);

				for (int i = 0; i < eachOrder.get(ind)[j].length; i++) {
					Log.i("OnResume", "in eachOrder.get(ind)[j] loop" + i);
					if (eachOrder.get(ind)[j][i] != 0) {
						Log.i("OnResume", "in if stmt" + i);
						LinearLayout linear1 = new LinearLayout(this);
						linear1.setOrientation(LinearLayout.HORIZONTAL);
						sublayout.addView(linear1);
						b = new TextView(this);
						if (i < 11) {
							b.setText(menuItems[i] + ": "
									+ eachOrder.get(ind)[j][i]);
						} else {
							b.setText(specialItemsMap.get(ind).get(i - 11)
									+ ": " + eachOrder.get(ind)[j][i]);
						}
						b.setId(i);
						b.setTextSize(20);
						b.setPadding(18, 13, 18, 13);
						b.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
						b.setLayoutParams(new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));

						linear1.addView(b);
					}
				}
			}
			linearlayout.addView(linear2);
			linearlayout.addView(linearGap);

			Button d = new Button(this);
			d.setText("Done");
			d.setId(ind);
			d.setTextSize(15);
			d.setPadding(8, 3, 8, 3);
			d.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
			d.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			linear2.addView(d);

			TextView e = new TextView(this);
			e.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 40));
			linearGap.addView(e);

			d.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					sendAlert(clientIDList.get(v.getId()));
					linearlayout.removeView(linearHeader);
					Log.i("after linearHeader", "");
					linearlayout.removeView(linear0);
					Log.i("after linearHeader", "");
					linearlayout.removeView(linear2);
					Log.i("after linearHeader", "");
					linearlayout.removeView(linearGap);
					orders.remove(v.getId());
					Log.i("after linearHeader", "");
					clientIDList.remove(v.getId());
					Log.i("after linearHeader", "");
					eachOrder.remove(v.getId());
				}
			});

			this.setContentView(scrollview);
		}
		// Kokanee, Budweiser, Chivas, Ballantines, Pizza, Hamburger, Sandwich,
		// French Fries, Edamame, Salad, Cheese, Special 1, Special 2

		// Log.i("MY_MESSAGE", "in onResume (ViewOrdersPage)");
		// final LinearLayout parentlayout = new LinearLayout(this);
		// ScrollView scrollview;
		// scrollview = new ScrollView(this);
		// parentlayout.setOrientation(LinearLayout.VERTICAL);
		// scrollview.addView(parentlayout);
		// TextView a,b;
		// int ind;
		//
		//
		// for (ind=0; ind < orders.size();ind++) {
		// String s = orders.get(ind).toString();
		// int sentinel = s.indexOf('#');
		// String str = s.substring(0,sentinel);
		// int clientID = Integer.parseInt(str);
		// clientIDList.add(clientID);
		// s = s.replace(str+"#","");
		//
		// final LinearLayout linearHeader = new LinearLayout(this);
		// final LinearLayout linear0 = new LinearLayout(this);
		// final LinearLayout linear1 = new LinearLayout(this);
		// final LinearLayout linearGap = new LinearLayout(this);
		// linearHeader.setOrientation(LinearLayout.HORIZONTAL);
		// linear0.setOrientation(LinearLayout.HORIZONTAL);
		// linear1.setOrientation(LinearLayout.HORIZONTAL);
		// linearGap.setOrientation(LinearLayout.HORIZONTAL);
		// parentlayout.addView(linearHeader);
		// parentlayout.addView(linear0);
		// parentlayout.addView(linear1);
		// parentlayout.addView(linearGap);
		//
		// a = new TextView(this);
		//
		//
		// a.setId(ind);
		// a.setText("Client #"+ clientID +":");
		// a.setTextSize(15);
		// a.setPadding(18, 13, 18, 13);
		// a.setTypeface(null, Typeface.BOLD);
		// a.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		// linearHeader.addView(a);
		//
		//
		// b = new TextView(this);
		// b.setText(s);
		// b.setId(50);
		// b.setTextSize(15);
		// b.setPadding(18, 13, 18, 13);
		// b.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
		// b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		// linear0.addView(b);
		//
		// Button d = new Button(this);
		// d.setText("Done");
		// d.setId(ind);
		// d.setTextSize(15);
		// d.setPadding(8, 3, 8, 3);
		// d.setTypeface(Typeface.SERIF, Typeface.BOLD_ITALIC);
		// d.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		// LayoutParams.WRAP_CONTENT));
		// linear1.addView(d);
		//
		// TextView e = new TextView(this);
		// e.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		// 40));
		// linearGap.addView(e);
		//
		// d.setOnClickListener(new View.OnClickListener(){
		//
		// @Override
		// public void onClick(View v) {
		// parentlayout.removeView(linearHeader);
		// parentlayout.removeView(linear0);
		// parentlayout.removeView(linear1);
		// parentlayout.removeView(linearGap);
		// clientIDList.remove(v.getId());
		// orders.remove(v.getId());
		// sendAlert(clientIDList.get(v.getId()));
		// }
		// });
		//
		// this.setContentView(scrollview);
		//
		// }
	}

	private void sendAlert(int clientID) {
		Log.i("inside SendAlert", "" + clientID);
		ConnectionApplication app = (ConnectionApplication) getApplication();
		// byte buf[] = new byte[msg.length() + 2];
		byte buf[] = new byte[2];
		buf[0] = (byte)clientID;
		Log.i("after","");
		buf[1] = (byte) 5; // 5 means order is completed;
		Log.i("test","");
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
