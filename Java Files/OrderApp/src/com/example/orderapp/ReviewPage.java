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
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review_page, menu);
		
		
		
		Bundle extras = getIntent().getExtras();
		if (extras != null)
		    Alcohol = extras.getIntArray("Alcohol_Data");
		
		StringBuilder strb = new StringBuilder("Order: \n");
		int i;
		for(i=0;i<Alcohol.length;i++){
			if(Alcohol[i]!=0){
				strb.append(com.example.orderapp.Alcohol.getAlcohols(i)+":   "+ Alcohol[i]+ "\n");				
			}
		}
		
		
		LinearLayout top_linear_layout =(LinearLayout) findViewById(R.id.LinearLayout1);
		TextView textView1 = new TextView(this);
		textView1.setText(strb);
		top_linear_layout.addView(textView1);
		
		
		
		return true;
	}
	

	
//	strb.append( "Total items ordered = " + total_items + "\n");
//	strb.append( "Total Cost = " + total_price + "\n");
//	// Rather than writing to a pre-allocated field on the
//	// screen, create a new view, and write to it.
//	LinearLayout top_linear_layout =
//	(LinearLayout) findViewById(R.id.LinearLayout1);
//	TextView report_line = new TextView(this);
//	report_line.setText(strb);
//	top_linear_layout.addView(report_line);


}
