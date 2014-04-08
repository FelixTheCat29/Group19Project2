package com.example.orderapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class WelcomePage extends Activity {
	
	
    static Customer Customer1 = new Customer(1);
    static Customer Customer2 = new Customer(2);
    static Customer Customer3 = new Customer(3);
    static Customer Customer4 = new Customer(4);
    
    static Customer CustArray[] = {Customer1, Customer2, Customer3, Customer4};
	
	
	Button button;
	Button insidePopupButton;
	LinearLayout layoutOfPopup;
	TextView popupText;
	PopupWindow popupMessage;
    static double[] TableOrder;
	Button btnClosePopup;
	static int numCust = 1;
	int x = 0;
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
	
	public void Transistion(View view){
		
	EditText editText = (EditText) findViewById(R.id.editText1);
	if(editText.getText().toString().matches("")){
		x = 0;
	}
try{
	int message = Integer.parseInt(editText.getText().toString());	
	x = message;
{
	
}
}
catch(NumberFormatException nfe)
{
	
}
    
    numCust = x;

    TableOrder = new double[numCust];
    for(int i=0; i<TableOrder.length; i++)
    {
    	TableOrder[i]=0;
    }

    
    Intent intent = new Intent(this, NumberCustomers.class); 

    Intent intent2 = new Intent(this, ViewMenu.class);


    
    if (x == 1){
    	NumberCustomers.selectedCust = 1;
        NumberCustomers.CurrentCust = CustArray[0];
    startActivity(intent2);   	
    }
    
    if (x > 1 && x < 5){
    startActivity(intent);    
    }
    
    if (x > 4 || x == 0)
    {
    	initiatePopupWindow();
    	
    }
    
    
	}
	private PopupWindow pwindo;
	private void initiatePopupWindow() { 
		try { 
			
			MediaPlayer mp = MediaPlayer.create(this, R.raw.slayer);  
			  mp.start();
			
			
		// We need to get the instance of the LayoutInflater 
		LayoutInflater inflater = (LayoutInflater) WelcomePage.this 
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View layout = inflater.inflate(R.layout.popup,(ViewGroup)

		findViewById(R.id.popup_element)); 
		pwindo = new PopupWindow(layout, 450, 450, true); 
		pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

		btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup); 
		btnClosePopup.setOnClickListener(cancel_button_click_listener);

		btnClosePopup.setTextColor(Color.RED);
		
		
		} catch (Exception e) { 
		e.printStackTrace(); 
		} 
		}

		private OnClickListener cancel_button_click_listener = new OnClickListener() { 
		public void onClick(View v) { 
		pwindo.dismiss();

		}

		};
		public static int getNumCust(){
		return numCust;
			
		}
		
		public static void editTableOrder(int NumCust, Double sum)
		{
			TableOrder[NumCust]= sum;
		}
		
		public static double[] getTableOrder()
		{
		return TableOrder;
		}
		
}