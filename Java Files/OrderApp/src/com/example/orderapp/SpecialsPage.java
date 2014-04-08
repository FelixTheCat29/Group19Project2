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
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class SpecialsPage extends Activity {

	public static String specialOrders;

	public static String stringForUpdateSpecials;
	public static int counter=0;
	public static Handler mHandler;
	List<EditText> editTextList = new ArrayList<EditText>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_specials_page);

		//UI update Purpose, parses the string containing specialItem1
		mHandler = new Handler() {
			public void handleMessage(Message msg) {

				Log.i("UI",""+stringForUpdateSpecials);

				//				if(SpecialItem.specialItems.isEmpty())
				//					SpecialItem.specialItems.add(stringForUpdateSpecials);
				//				else 
				//					/SpecialItem.specialItems.set(0, stringForUpdateSpecials);
				//				
				//Parse the string into items and prices

				int endItem1 = stringForUpdateSpecials.indexOf('$');
				String item1 = stringForUpdateSpecials.substring(0,endItem1);
  
				Log.i("Spec", "Item1"+ item1);
				Log.i("Spec", "endItem1 "+endItem1);

				int endPrice1 = stringForUpdateSpecials.indexOf('*');
				String price1 = stringForUpdateSpecials.substring(endItem1+1,endPrice1);

				Log.i("Spec", "end index of price1 "+ endPrice1);
				Log.i("Spec", "price1 "+ price1);

				String afterItem1 = stringForUpdateSpecials.substring(endPrice1+1);

				Log.i("Spec", "afterItem1 "+ afterItem1);

				int endItem2 = afterItem1.indexOf('$');
				String item2 = afterItem1.substring(0,endItem2);

				Log.i("Spec", "end index of item2"+ endItem2);
				Log.i("Spec", "item2 "+ item2);

				int endPrice2 = afterItem1.indexOf('*');
				String price2 = afterItem1.substring(endItem2+1,endPrice2);

				Log.i("Spec", "end index of price2"+ endPrice2);
				Log.i("Spec", "price2 "+ price2);
				
				double price1double = Double.parseDouble(price1);
				double price2double = Double.parseDouble(price2);
				
				

				Log.i("Double", "Price 1 in double: " + price1double);
				Log.i("Double", "Price 2 in double: " +  price2double);//SpecialItem.specialItemPricesDouble

				if(SpecialItem.specialItemPrices.size() < 2 ){
					SpecialItem.specialItems.add(0, item1);
					SpecialItem.specialItemPrices.add(0, price1);
					SpecialItem.specialItemPricesDouble.add(0, price1double);
					
					SpecialItem.specialItems.add(1, item2);
					SpecialItem.specialItemPrices.add(1, price2);
					SpecialItem.specialItemPricesDouble.add(1, price2double);

				} else {

					SpecialItem.specialItems.set(0, item1);
					SpecialItem.specialItemPrices.set(0, price1);
					SpecialItem.specialItemPricesDouble.set(0, price1double);
					
					SpecialItem.specialItems.set(1, item2);
					SpecialItem.specialItemPrices.set(1, price2);
					SpecialItem.specialItemPricesDouble.set(1, price2double);
				}
				//Update the UI to display
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

		TextView b;
		TextView c;
		EditText e;

		for(int i=0; i<SpecialItem.specialItems.size(); i++) {

			String item = SpecialItem.specialItems.get(i);
			String price = SpecialItem.specialItemPrices.get(i);

			Log.i("Crash", "Index of for loop "+i);
			Log.i("Crash", "Item: "+ item);
			Log.i("Crash", "Price: " + price);

			LinearLayout linear0 = new LinearLayout(this);
			linear0.setOrientation(LinearLayout.HORIZONTAL);
			linearlayout.addView(linear0);

			b= new TextView(this);
			b.setText(item);
			b.setId(50);
			b.setTextSize(25);
			b.setPadding(18, 13, 18, 13);
			b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
			b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

			c = new TextView(this);
			c.setText(price);
			c.setId(50);
			c.setTextSize(25);
			c.setPadding(18, 13, 18, 13);
			c.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
			c.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

			e = new EditText(this);
			e.setId(i);
			e.setInputType(InputType.TYPE_CLASS_NUMBER);
			editTextList.add(e);

			linear0.addView(b);
			linear0.addView(c);
			linear0.addView(e); 
			this.setContentView(scrollview);
		}
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

		Button f = new Button(this);
		f.setText("Save and Continue");
		f.setId(2); 
		f.setTextSize(10);
		f.setPadding(8, 3, 8, 3);
		f.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
		f.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

		linearlayout.addView(f);            
		f.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {

				onClickSaveAndCont(v);
			}	
		});
		this.setContentView(scrollview);



	}

	public double calculateTotal() {
		double total=0.00;
		for(int i=0; i< SpecialItem.specialItemQuantity.size() ; i++) {
			total += SpecialItem.specialItemQuantity.get(i)* SpecialItem.specialItemPricesDouble.get(i);
			
		}
		Log.i("double", "total comes to: " + total);
		return total;
	}
	
	public void onClickSaveAndCont(View view){

		for(int i=0; i<editTextList.size(); i++) {
			String qtyString = editTextList.get(i).getText().toString();
			int qty = Integer.parseInt(qtyString);
			NumberCustomers.CurrentCust.special[i] =qty; 
			if(SpecialItem.specialItemQuantity.size() < 2 )
				SpecialItem.specialItemQuantity.add(i, qty);  
			else 
				SpecialItem.specialItemQuantity.set(i, qty);  
		}
		NumberCustomers.CurrentCust.SpeSum = calculateTotal();
		
		Intent intent = new Intent(this, ViewMenu.class);
		startActivity(intent);

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

	@Override
	public void onBackPressed() {
	}


}
