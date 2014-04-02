package com.example.chefapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

	public void Transistion(View view){
	    Intent intent = new Intent(this, Connection.class);
	    startActivity(intent); 
		
	}
}
