package com.example.orderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Appetizer extends Activity {
	
	int Edamame = 0;
	int Salad = 0;
	int Cheese = 0;
	//int Shrimp = 0;
	static int Appetizer[] = {0,0,0}; 
	int AppetizerKind = Appetizer.length;
	static double AppetizerPrice[] = {3.50,5.50,4.50};
	static double price = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appetizer);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.appetizer, menu);
		return true;
	}
	public static String getAppetizer(int i){
		String Al[] = {"Edamame","Salad","Cheese"};
		
		return Al[i];
	}
	
	public static int[] getAl(){
		return Appetizer;// = {"Kokanee","Chivas","Budweiser","Ballantines"};
		
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
		EditText EditText3 = (EditText) findViewById(R.id.editText4);
		//EditText EditText4 = (EditText) findViewById(R.id.editText4);
		
		
		if(EditText1.getText().toString().matches("")){
			Edamame = 0;
		}
		if(EditText2.getText().toString().matches("")){
			Salad = 0;
		}
		if(EditText3.getText().toString().matches("")){
			Cheese = 0;
		}
		/*if(EditText4.getText().toString().matches("")){
			Shrimp = 0;
		}
		*/
		try{
			int message1 = Integer.parseInt(EditText1.getText().toString());	
			//NumberCustomers.CurrentCust.alcohol[0] = message1;
			Edamame = message1;
			NumberCustomers.CurrentCust.appetizer[0] = message1;
		}
			catch(NumberFormatException nfe)
			{
				Edamame = 0;
				NumberCustomers.CurrentCust.appetizer[0] = 0;
				//NumberCustomers.CurrentCust.alcohol[0] = 0;
			}
		
		try{
			int message2 = Integer.parseInt(EditText2.getText().toString());	
			Salad = message2;
			NumberCustomers.CurrentCust.appetizer[1] = message2;
			//NumberCustomers.CurrentCust.alcohol[1] = message2;
		}
			catch(NumberFormatException nfe)
			{
				Salad = 0;
				NumberCustomers.CurrentCust.appetizer[1] = 0;
				//NumberCustomers.CurrentCust.alcohol[1] = 0;
			}
		try{
			int message3 = Integer.parseInt(EditText3.getText().toString());	
			Cheese = message3;
			NumberCustomers.CurrentCust.appetizer[2] = message3;
			//NumberCustomers.CurrentCust.alcohol[2] = message3;
		}
			catch(NumberFormatException nfe)
			{
				Cheese = 0;
				NumberCustomers.CurrentCust.appetizer[2] = 0;
				//NumberCustomers.CurrentCust.alcohol[2] = 0;
			}
	/*	try{
			int message4 = Integer.parseInt(EditText4.getText().toString());	
		//	NumberCustomers.CurrentCust.alcohol[3] = 0;
			Shrimp = message4;
		}
			catch(NumberFormatException nfe)
			{
		//		NumberCustomers.CurrentCust.alcohol[3] = 0;
				Shrimp = 0;
			}
		*/	
		Appetizer[0] = Edamame;
		Appetizer[1] = Salad;
		Appetizer[2] = Cheese;
		//Appetizer[3] = Shrimp;
		
		//NumberCustomers.CurrentCust.CustAppetizer(Appetizer);
	
		
		for(int i=0; i<AppetizerKind; i++)
		{
			price = AppetizerPrice[i]*Appetizer[i]+price;
		}
		
		NumberCustomers.CurrentCust.AppetizerSum = price;
		
		Intent intent = new Intent(this, ViewMenu.class);
		//intent.putExtra("Alcohol_Data", Alcohol);
		startActivity(intent);
		
		
	}
	@Override
	public void onBackPressed() {
	}
	
}
