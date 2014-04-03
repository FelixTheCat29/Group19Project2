package com.example.chefapp;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class UpdateSpecials extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_specials);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_update_specials, menu);
		return true;
	}
	
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Log.i("MY_MESSAGE", "in onResume (MainActivity)");
    }
   
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.i("MY_MESSAGE", "in onPause (MainActivity)");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	Log.i("MY_MESSAGE", "in onStop (MainActivity)");
    }
   
	
}
