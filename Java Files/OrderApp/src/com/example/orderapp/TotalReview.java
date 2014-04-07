package com.example.orderapp;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class TotalReview extends Activity {
	static String AlcoholOrder="";
	static double totalSum=0;
	static double[] TableOrder;
	static String OrderString="";
    
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
	        
	        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////customer1
	       //Dynamically print "Below is the order for quest#" on top of the screen
	        LinearLayout linear0 = new LinearLayout(this);
            linear0.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear0);
	        a = new TextView(this);
            a.setText("Below is the order for guest 1");
            a.setId(50);
            a.setTextSize(25);
            a.setPadding(18, 13, 18, 13);
            a.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            a.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            linear0.addView(a);
            this.setContentView(scrollview);
            
            
            //print the order for Alcohol on the screen
	        for(int i = 0 ; i < WelcomePage.Customer1.alcohol.length ; i++)
	        {	
	        	int array1[] = WelcomePage.Customer1.alcohol;
	        	if (array1[i] != 0)
	        	{	
	        		LinearLayout linear1 = new LinearLayout(this);
                    linear1.setOrientation(LinearLayout.HORIZONTAL);
                    linearlayout.addView(linear1);
                    b = new TextView(this);
                    //AlcoholOrder += Alcohol.getAlcohols(i)+": "+ array1[i];
                    WelcomePage.Customer1.customerSum += Alcohol.getAlcohols(i)+": "+ array1[i];
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
	        
	        //print the order for Main Menu on the screen
	        for(int i = 0 ; i < WelcomePage.Customer1.mainmenu.length ; i++)
	        {	
	        	int array1[] = WelcomePage.Customer1.mainmenu;
	        	if (array1[i] != 0)
	        	{	
	        		LinearLayout linear1 = new LinearLayout(this);
                    linear1.setOrientation(LinearLayout.HORIZONTAL);
                    linearlayout.addView(linear1);
                    b = new TextView(this);
                    //AlcoholOrder += Main_menu.getMainDishes(i)+": "+ array1[i];
                    WelcomePage.Customer1.customerSum += Main_menu.getMainDishes(i)+": "+ array1[i];
                    b.setText(Main_menu.getMainDishes(i)+": "+ array1[i]);
                    b.setId(i);
                    b.setTextSize(20);
                    b.setPadding(18, 13, 18, 13);
		            b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
		            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			           
	            linear1.addView(b);
	        	}
	  
	            this.setContentView(scrollview);
	        }
	        
	        
	      //print the order for Appetizer on the screen
	        for(int i = 0 ; i < WelcomePage.Customer1.appetizer.length ; i++)
	        {	
	        	int array1[] = WelcomePage.Customer1.appetizer;
	        	if (array1[i] != 0)
	        	{	
	        		LinearLayout linear1 = new LinearLayout(this);
                    linear1.setOrientation(LinearLayout.HORIZONTAL);
                    linearlayout.addView(linear1);
                    b = new TextView(this);
                    //AlcoholOrder += Appetizer.getAppetizer(i)+": "+ array1[i];
                    WelcomePage.Customer1.customerSum += Appetizer.getAppetizer(i)+": "+ array1[i];
                    b.setText(Appetizer.getAppetizer(i)+": "+ array1[i]);
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
	        DecimalFormat df = new DecimalFormat("#0.00");
            c.setText("Below is the total price for guest "+ NumberCustomers.getSelCust()+": $"
	        +String.valueOf(df.format(WelcomePage.Customer1.totalCustOrder())));
            c.setId(50);
            c.setTextSize(25);
            c.setPadding(18, 13, 18, 13);
            c.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            c.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            linear2.addView(c);
            this.setContentView(scrollview);
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// customer2
 	       //Dynamically print "Below is the order for quest#" on top of the screen
	       // LinearLayout linear0 = new LinearLayout(this);
            linear0.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear0);
	        a = new TextView(this);
            a.setText("Below is the order for guest 1");
            a.setId(50);
            a.setTextSize(25);
            a.setPadding(18, 13, 18, 13);
            a.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            a.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            linear0.addView(a);
            this.setContentView(scrollview);
            
            
            //print the order for Alcohol on the screen
	        for(int i = 0 ; i < WelcomePage.Customer2.alcohol.length ; i++)
	        {	
	        	int array1[] = WelcomePage.Customer2.alcohol;
	        	if (array1[i] != 0)
	        	{	
	        		LinearLayout linear1 = new LinearLayout(this);
                    linear1.setOrientation(LinearLayout.HORIZONTAL);
                    linearlayout.addView(linear1);
                    b = new TextView(this);
                    //AlcoholOrder += Alcohol.getAlcohols(i)+": "+ array1[i];
                    WelcomePage.Customer2.customerSum += Alcohol.getAlcohols(i)+": "+ array1[i];
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
	        
	        //print the order for Main Menu on the screen
	        for(int i = 0 ; i < WelcomePage.Customer2.mainmenu.length ; i++)
	        {	
	        	int array1[] = WelcomePage.Customer2.mainmenu;
	        	if (array1[i] != 0)
	        	{	
	        		LinearLayout linear1 = new LinearLayout(this);
                    linear1.setOrientation(LinearLayout.HORIZONTAL);
                    linearlayout.addView(linear1);
                    b = new TextView(this);
                    //AlcoholOrder += Main_menu.getMainDishes(i)+": "+ array1[i];
                    WelcomePage.Customer2.customerSum += Main_menu.getMainDishes(i)+": "+ array1[i];
                    b.setText(Main_menu.getMainDishes(i)+": "+ array1[i]);
                    b.setId(i);
                    b.setTextSize(20);
                    b.setPadding(18, 13, 18, 13);
		            b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
		            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			           
	            linear1.addView(b);
	        	}
	  
	            this.setContentView(scrollview);
	        }
	        
	        
	      //print the order for Appetizer on the screen
	        for(int i = 0 ; i < WelcomePage.Customer2.appetizer.length ; i++)
	        {	
	        	int array1[] = WelcomePage.Customer2.appetizer;
	        	if (array1[i] != 0)
	        	{	
	        		LinearLayout linear1 = new LinearLayout(this);
                    linear1.setOrientation(LinearLayout.HORIZONTAL);
                    linearlayout.addView(linear1);
                    b = new TextView(this);
                    //AlcoholOrder += Appetizer.getAppetizer(i)+": "+ array1[i];
                    WelcomePage.Customer2.customerSum += Appetizer.getAppetizer(i)+": "+ array1[i];
                    b.setText(Appetizer.getAppetizer(i)+": "+ array1[i]);
                    b.setId(i);
                    b.setTextSize(20);
                    b.setPadding(18, 13, 18, 13);
		            b.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
		            b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			           
	            linear1.addView(b);
	        	}
	  
	            this.setContentView(scrollview);
	        }
	        
	        
            linear2.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear2);
	        c = new TextView(this);
	        c.setText("Below is the total price for guest 2: $"
	        +String.valueOf(df.format(WelcomePage.Customer2.totalCustOrder())));
            c.setId(50);
            c.setTextSize(25);
            c.setPadding(18, 13, 18, 13);
            c.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);
            c.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            linear2.addView(c);
            this.setContentView(scrollview);
            
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// customer3
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
     
           
            
            //go back for different customer
            Button e = new Button(this);
              
            e.setText("Order For a Different Guest");
            e.setId(2); 
            e.setTextSize(10);
            e.setPadding(8, 3, 8, 3);
            e.setTypeface(Typeface.SERIF,Typeface.BOLD_ITALIC);

           
          
            linear3.addView(d);            
            d.setOnClickListener(new View.OnClickListener(){
                
                @Override
                public void onClick(View v) {
                      // TODO Auto-generated method stub
                      //Toast.makeText(getApplicationContext(), "Button Clicked.."+ (v.getId()+1), Toast.LENGTH_SHORT).show();
                		JumpAct(v);
                }
                
                
                
            });
            
            if(WelcomePage.getNumCust() != 1)
            {  
            linear3.addView(e);            
            e.setOnClickListener(new View.OnClickListener(){
                
                @Override
                public void onClick(View v) {
                	transitionNumCust(v);
                }
                
                
                
            });
            }
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
		startActivity(intent);
	}
	
	public void transitionNumCust(View view) {
		Intent intent = new Intent(this, NumberCustomers.class);
		startActivity(intent);
	}
	
	public static double OrderSum(){
		totalSum = Alcohol.getAlPrice();
		TableSum(NumberCustomers.getSelCust(), totalSum);
		return totalSum;
	}
	
	public static String OrderString(){
		
		
		OrderString= "";
		//OrderString= AlcoholOrder;
		AlcoholOrder="";
		OrderString = WelcomePage.Customer1.customerSum;
		
		Log.i("debug", "Customer sum: " + WelcomePage.Customer1.customerSum);
		Log.i("debug", "Alcoho Order: " + AlcoholOrder);
		Log.i("debug", "OrderString"  + OrderString);
		
		return OrderString;
		
	}
	
	public static void TableSum(int SelCust, double totalSum){
		WelcomePage.editTableOrder((SelCust-1),totalSum);
	}
	
	public static double ReturnTableSum(){
		double sum = 0;
	    for(int i=0; i<WelcomePage.TableOrder.length; i++)
	    {
	    	sum+= WelcomePage.TableOrder[i];
	    }
		return sum;
	}
	
}
