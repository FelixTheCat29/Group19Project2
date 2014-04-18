package com.example.orderapp;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, Connection.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onClickAlcohol(View view){
		
		Intent intent4 = new Intent(this, Alcohol.class);
		startActivity(intent4);
		
	}
	
	public void onClickMainMenu(View view){
		Intent intent5 = new Intent(this, Main_menu.class);
		startActivity(intent5);
	}
	
	public void onClickAppetizer(View view){
		Intent intent6 = new Intent(this, Appetizer.class);
		startActivity(intent6);
	}
	
	public void onClickReviewAndCheckout(View view){
		Intent intent = new Intent(this, ReviewPage.class);
		startActivity(intent);
		
	}
	
	public void onClickConnect(View view) {
		Intent intent = new Intent(this, Connection.class);
		//intent.putExtra("Alcohol_Data", Alcohol);
		startActivity(intent);
	}
	
	public void onClickSpecials(View view) {
		Intent intent = new Intent(this, SpecialsPage.class);
		startActivity(intent);
	}

	
}
