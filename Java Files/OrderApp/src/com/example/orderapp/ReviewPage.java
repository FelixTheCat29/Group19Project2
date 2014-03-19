package com.example.orderapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReviewPage extends Activity {
	
    int[] Alcohol;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_page);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null)
		    Alcohol = extras.getIntArray("Alcohol_Data");
		
		
		StringBuilder strb = new StringBuilder("Order: \n");
		
		for(int i=0;i<Alcohol.length;i++){
			if(Alcohol[i]!=0){
				strb.append(com.example.orderapp.Alcohol.getAlcohols(i)+":   "+ Alcohol[i]+ "\n");				
			}
		}
		
	
		TextView textView1 = new TextView(this);
		textView1.setText(strb);
		
	

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review_page, menu);
		return true;
	}
	


}
