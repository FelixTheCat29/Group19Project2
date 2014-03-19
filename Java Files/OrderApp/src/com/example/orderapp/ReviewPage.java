package com.example.orderapp;

import java.text.DecimalFormat;

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

	
	      ScrollView scrollview;
		  	scrollview = new ScrollView(this);
	        LinearLayout linearlayout = new LinearLayout(this);
	        linearlayout.setOrientation(LinearLayout.VERTICAL);
	        scrollview.addView(linearlayout);
	        TextView b;
	        TextView a;
	        TextView c;
	        
	       //Dynamically print "Below is the order for quest#" on top of the screen
	        LinearLayout linear0 = new LinearLayout(this);
            linear0.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear0);
	        a = new TextView(this);
            a.setText("Below is the order for guest "+NumberCustomers.getSelCust()+":");
            a.setId(50);
            a.setTextSize(25);
            a.setPadding(18, 13, 18, 13);
            a.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            a.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            linear0.addView(a);
            this.setContentView(scrollview);
            
            //print the order for Alcohol on the screen
	        for(int i = 0 ; i < Alcohol.getAl().length ; i++)
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
                    b.setTextSize(20);
                    b.setPadding(18, 13, 18, 13);
		            b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
		            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			           
	            linear1.addView(b);
	        	}
	        	
	            this.setContentView(scrollview);
	        }
	        
	        LinearLayout linear2 = new LinearLayout(this);
            linear2.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear2);
	        c = new TextView(this);
	        DecimalFormat df = new DecimalFormat("#.##");
            c.setText("Below is the total price for guest "+NumberCustomers.getSelCust()+": $"
	        +String.valueOf(df.format(Alcohol.getAlPrice()))+".00");
            c.setId(50);
            c.setTextSize(25);
            c.setPadding(18, 13, 18, 13);
            c.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            c.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            linear2.addView(c);
            this.setContentView(scrollview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review_page, menu);
		return true;
	}
	


}
