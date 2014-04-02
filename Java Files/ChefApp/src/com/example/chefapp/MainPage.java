package com.example.chefapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
<<<<<<< HEAD
import android.view.MenuInflater;
=======
import android.view.MenuItem;
>>>>>>> 1b71a604dc200f3446697790d475d4c4fa1bfacf
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
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	public void Transistion(View view){
	    Intent intent = new Intent(this, Connection.class);
	    startActivity(intent); 
		
	}
	
	
}
