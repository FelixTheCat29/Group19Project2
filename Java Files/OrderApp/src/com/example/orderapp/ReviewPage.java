package com.example.orderapp;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ReviewPage extends Activity {
	static String alcoholSum="";
	static String totalSum="";

    
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
	        {	//array1[] should be outside of the for loop
	        	int array1[] = Alcohol.getAl();
	        	if (array1[i] != 0)
	        	{	
	        		LinearLayout linear1 = new LinearLayout(this);
                    linear1.setOrientation(LinearLayout.HORIZONTAL);
                    linearlayout.addView(linear1);
                    b = new TextView(this);
                    alcoholSum += Alcohol.getAlcohols(i)+": "+ array1[i];
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
            
	        LinearLayout linear3 = new LinearLayout(this);
            linear3.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear3);
            Button d = new Button(this);
            d.setText("Confirm Order");
            d.setId(1); 
            d.setTextSize(10);
            d.setPadding(8, 3, 8, 3);
            d.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            d.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
           
            
            linear3.addView(d);            
            d.setOnClickListener(new View.OnClickListener(){
                
                @Override
                public void onClick(View v) {
                      // TODO Auto-generated method stub
                      //Toast.makeText(getApplicationContext(), "Button Clicked.."+ (v.getId()+1), Toast.LENGTH_SHORT).show();
                		JumpAct(v);
                }
            });
             this.setContentView(scrollview);
	}
	
	private void JumpAct(View v) {
		// TODO Auto-generated method stub
        Intent intent = new Intent(this, Connection.class);
        startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review_page, menu);
		return true;
	}
	
	public void onClickConnect(View view) {
		Intent intent = new Intent(this, Connection.class);
		//intent.putExtra("Alcohol_Data", Alcohol);
		startActivity(intent);
	}
	
	public static String OrderSum(){
		totalSum ="";
		totalSum = alcoholSum;
		alcoholSum = "";
		return totalSum;
	}
	
}
