package com.example.orderapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SpecialItem {
	
	public static List<String> specialItems = new ArrayList<String>();
	public static List<String> specialItemPrices = new ArrayList<String>();
	public static List<Integer> specialItemQuantity = new ArrayList<Integer>();
	public static List<Double> specialItemPricesDouble = new ArrayList<Double>();
	
}
	
//	public static int[] getAl(){
//		return Alcohol;// = {"Kokanee","Chivas","Budweiser","Ballantines"};
//		
//	}
//	
//	public static double getAlPrice(){
//		return price;
//		
//	}
//	
//	public void onClickSaveAndCont(View view){
//
//		price = 0;
//		//buffer to send to DE2
////		int Alcohol[] = {0,0,0,0}; //fixed elements just for now : Alcohol[kok, Bud, Chivas, Ballan]
//		
//		EditText EditText1 = (EditText) findViewById(R.id.editText1);
//		EditText EditText2 = (EditText) findViewById(R.id.editText2);
//		EditText EditText3 = (EditText) findViewById(R.id.editText3);
//		EditText EditText4 = (EditText) findViewById(R.id.editText4);
//		
//		
//		if(EditText1.getText().toString().matches("")){
//			Kokanee = 0;
//		}
//		if(EditText2.getText().toString().matches("")){
//			Chivas = 0;
//		}
//		if(EditText3.getText().toString().matches("")){
//			Budweiser = 0;
//		}
//		if(EditText4.getText().toString().matches("")){
//			Ballantines = 0;
//		}
//		try{
//			int message1 = Integer.parseInt(EditText1.getText().toString());	
//			Kokanee = message1;
//		}
//			catch(NumberFormatException nfe)
//			{
//				Kokanee = 0;
//			}
//		
//		try{
//			int message2 = Integer.parseInt(EditText2.getText().toString());	
//			Chivas = message2;
//		}
//			catch(NumberFormatException nfe)
//			{
//				Chivas = 0;
//			}
//		try{
//			int message3 = Integer.parseInt(EditText3.getText().toString());	
//			Budweiser = message3;
//		}
//			catch(NumberFormatException nfe)
//			{
//				Budweiser = 0;
//			}
//		try{
//			int message4 = Integer.parseInt(EditText4.getText().toString());	
//			Ballantines = message4;
//		}
//			catch(NumberFormatException nfe)
//			{
//				Ballantines = 0;
//			}
//			
//		Alcohol[0] = Kokanee;
//		Alcohol[1] = Chivas;
//		Alcohol[2] = Budweiser;
//		Alcohol[3] = Ballantines;
//		
//		for(int i=0; i<AlcoholKind; i++)
//		{
//			price = AlcoholPrice[i]*Alcohol[i]+price;
//		}
//		
//		Intent intent = new Intent(this, ViewMenu.class);
//		//intent.putExtra("Alcohol_Data", Alcohol);
//		startActivity(intent);
//		
//		
//	}
//	
//	
//}
//
