package com.example.orderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ViewMenu extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_menu);
		TextView textViewToChange = (TextView) findViewById(R.id.text111);
		textViewToChange.setText("Currently ordering for guest "+NumberCustomers.getSelCust()+":");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_menu, menu);
		
		return true;
		}
	
	public void onClickAlcohol(View view){
		
		Intent intent4 = new Intent(this, Alcohol.class);
		startActivity(intent4);
		
	}
	
	public void onClickReviewAndCheckout(View view){
		Intent intent = new Intent(this, ReviewPage.class);
		startActivity(intent);
		
	}
	
}
