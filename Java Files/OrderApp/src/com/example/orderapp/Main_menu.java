package com.example.orderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Main_menu extends Activity {
	
	int Pizza = 0;
	int hamburger = 0;
	int fries = 0;
	int sandwich = 0;
	static int MainMenu[] = {0,0,0,0}; //fixed elements just for now : Alcohol[kok, Bud, Chivas, Ballan]
	int MainMenuKind = MainMenu.length;
	static double MainMenuPrice[] = {5.00,7.00,5.00,6.00};
	static double price = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alcohol, menu);
		return true;
	}
	public static String getAlcohols(int i){
		String Al[] = {"Pizza","Hamburger","Fries","Sandwich"};
		
		return Al[i];
	}
	
	public static int[] getAl(){
		return MainMenu;
		
	}
	
	public static double getAlPrice(){
		return price;
	}
	
	
	public void onClickSaveAndCont(View view){

		price = 0;
		//buffer to send to DE2
//		int Alcohol[] = {0,0,0,0}; //fixed elements just for now : Alcohol[kok, Bud, Chivas, Ballan]
		
		EditText EditText1 = (EditText) findViewById(R.id.editText1);
		EditText EditText2 = (EditText) findViewById(R.id.editText2);
		EditText EditText3 = (EditText) findViewById(R.id.editText3);
		EditText EditText4 = (EditText) findViewById(R.id.editText4);
		
		
		if(EditText1.getText().toString().matches("")){
			Pizza = 0;
		}
		if(EditText2.getText().toString().matches("")){
			fries = 0;
		}
		if(EditText3.getText().toString().matches("")){
			hamburger = 0;
		}
		if(EditText4.getText().toString().matches("")){
			sandwich = 0;
		}
		try{
			int message1 = Integer.parseInt(EditText1.getText().toString());	
			//NumberCustomers.CurrentCust.alcohol[0] = message1;
			Pizza = message1;
		}
			catch(NumberFormatException nfe)
			{
				Pizza = 0;
				//NumberCustomers.CurrentCust.alcohol[0] = 0;
			}
		
		try{
			int message2 = Integer.parseInt(EditText2.getText().toString());	
			fries = message2;
			//NumberCustomers.CurrentCust.alcohol[1] = message2;
		}
			catch(NumberFormatException nfe)
			{
				fries = 0;
				//NumberCustomers.CurrentCust.alcohol[1] = 0;
			}
		try{
			int message3 = Integer.parseInt(EditText3.getText().toString());	
			hamburger = message3;
			//NumberCustomers.CurrentCust.alcohol[2] = message3;
		}
			catch(NumberFormatException nfe)
			{
				hamburger = 0;
				//NumberCustomers.CurrentCust.alcohol[2] = 0;
			}
		try{
			int message4 = Integer.parseInt(EditText4.getText().toString());	
		//	NumberCustomers.CurrentCust.alcohol[3] = 0;
			sandwich = message4;
		}
			catch(NumberFormatException nfe)
			{
		//		NumberCustomers.CurrentCust.alcohol[3] = 0;
				sandwich = 0;
			}
			
		MainMenu[0] = Pizza;
		MainMenu[1] = fries;
		MainMenu[2] = hamburger;
		MainMenu[3] = sandwich;
		
		NumberCustomers.CurrentCust.CustMain(MainMenu);
	
		
		for(int i=0; i<MainMenuKind; i++)
		{
			price = MainMenuPrice[i]*MainMenu[i]+price;
		}
		
		NumberCustomers.CurrentCust.MainMenuSum = price;
		
		Intent intent = new Intent(this, ViewMenu.class);
		//intent.putExtra("Alcohol_Data", Alcohol);
		startActivity(intent);
		
		
	}
	
	
}

