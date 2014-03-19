package com.example.orderapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ReviewPage extends Activity {
	

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_review_page);
		/*
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
		*/
	
	      ScrollView scrollview;
		  	scrollview = new ScrollView(this);
	        LinearLayout linearlayout = new LinearLayout(this);
	        linearlayout.setOrientation(LinearLayout.VERTICAL);
	        scrollview.addView(linearlayout);
	        TextView b;
	       
	        for(int i = 0; i<Alcohol.getAl().length;i++)
	        {
	        	int array1[] = Alcohol.getAl();
	        	if (array1[i] != 0)
	        	{
	            LinearLayout linear1 = new LinearLayout(this);
	            linear1.setOrientation(LinearLayout.HORIZONTAL);
	            linearlayout.addView(linear1);
	            b = new TextView(this);
	            b.setText(Alcohol.getAlcohols(i)+": "+ array1[i]);
	            b.setId(i);
	            b.setTextSize(10);
	            b.setPadding(8, 3, 8, 3);
	            b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
	            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	           
	            linear1.addView(b);
	        	}
	        	
	            this.setContentView(scrollview);
	        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review_page, menu);
		return true;
	}
	


}
