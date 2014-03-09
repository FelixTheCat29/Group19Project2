package com.example.orderapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.view.View.OnClickListener;

public class WelcomePage extends Activity {
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addListenerOnButton() {
		 
		final Context context = this;
 
		button = (Button) findViewById(R.id.buttonNext);
 
		
		
		
		
		button.setOnClickListener(new OnClickListener() {
 
			
			
			@Override
			public void onClick(View arg0) {
				EditText editText = (EditText) findViewById(R.id.editText1);
			    String message = editText.getText().toString();
			    Integer x = Integer.valueOf(message);

			    Intent intent = new Intent(context, SplitBill.class); 
                Intent intent2 = new Intent(context, ViewMenu.class);

                if (x == 1){
                startActivity(intent2);   	
                }
                
                if (x > 1){
                startActivity(intent);
                }

			}
 
		});
 
	}

 
}