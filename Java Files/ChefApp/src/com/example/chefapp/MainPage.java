package com.example.chefapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_page, menu);
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

	public void Transistion(View view){
	    Intent intent = new Intent(this, Connection.class);
	    startActivity(intent); 
		
	}
	
	
}
