package com.example.orderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Alcohol extends Activity {
	
	int Kokanee = 0;
	int Budweiser = 0;
	int Chivas = 0;
	int Ballantines = 0;
	static int Alcohol[] = {0,0,0,0}; //fixed elements just for now : Alcohol[kok, Bud, Chivas, Ballan]
	int AlcoholKind = Alcohol.length;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alcohol);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alcohol, menu);
		return true;
	}
	public static String getAlcohols(int i){
		String Al[] = {"Kokanee","Chivas","Budweiser","Ballantines"};
		
		return Al[i];
	}
	
	public static int[] getAl(){
		return Alcohol;// = {"Kokanee","Chivas","Budweiser","Ballantines"};
		
	}
	
	public void onClickSaveAndCont(View view){

		
		//buffer to send to DE2
//		int Alcohol[] = {0,0,0,0}; //fixed elements just for now : Alcohol[kok, Bud, Chivas, Ballan]
		
		EditText editText1 = (EditText) findViewById(R.id.editText1);
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		EditText editText3 = (EditText) findViewById(R.id.editText3);
		EditText editText4 = (EditText) findViewById(R.id.editText4);
		
		
		if(editText1.getText().toString().matches("")){
			Kokanee = 0;
		}
		if(editText2.getText().toString().matches("")){
			Chivas = 0;
		}
		if(editText3.getText().toString().matches("")){
			Budweiser = 0;
		}
		if(editText4.getText().toString().matches("")){
			Ballantines = 0;
		}
		try{
			int message1 = Integer.parseInt(editText1.getText().toString());	
			Kokanee = message1;
		}
			catch(NumberFormatException nfe)
			{
				Kokanee = 0;
			}
		
		try{
			int message2 = Integer.parseInt(editText2.getText().toString());	
			Chivas = message2;
		}
			catch(NumberFormatException nfe)
			{
				Chivas = 0;
			}
		try{
			int message3 = Integer.parseInt(editText3.getText().toString());	
			Budweiser = message3;
		}
			catch(NumberFormatException nfe)
			{
				Budweiser = 0;
			}
		try{
			int message4 = Integer.parseInt(editText4.getText().toString());	
			Ballantines = message4;
		}
			catch(NumberFormatException nfe)
			{
				Ballantines = 0;
			}
			
		Alcohol[0] = Kokanee;
		Alcohol[1] = Chivas;
		Alcohol[2] = Budweiser;
		Alcohol[3] = Ballantines;
		
		Intent intent = new Intent(this, ViewMenu.class);
		//intent.putExtra("Alcohol_Data", Alcohol);
		startActivity(intent);
		
		
	}
	
	
}

