package com.example.orderapp;

public class Customer {

	int SelCust;

	static String customerSum; 
	int alcohol[] ={0,0,0,0};
	int mainmenu[] ={0,0,0,0};
	int appetizer[]={0,0,0};
	int special[]={0,0};

	double Alsum;
	double MainMenuSum;
	double AppetizerSum;
	double SpeSum;
	double orderSum = 0;

	public Customer (int i)
	{
		String customerSum="";
		SelCust= i;
		int alcohol[] ={0,0,0,0};
		int mainmenu[] ={0,0,0,0};
		int appetizer[]={0,0,0};
		int special[]={0,0};

		double Alsum;
		double MainMenuSum;
		double AppetizerSum;
		double SpeSum;
	}

	public void CustMain(int order[])
	{
		mainmenu = order;
	}

	public void CustAppetizer(int order[])
	{
		appetizer=order;
	}


	public void CustAl(int order[])
	{
		alcohol = order;
	}


	public void calculateTotal(double alcohol, double mainmenu, double appetizer, double special)
	{
		orderSum = alcohol + mainmenu + appetizer + special;
	}

	public double totalCustOrder()
	{
		calculateTotal(Alsum, MainMenuSum, AppetizerSum, SpeSum);
		return orderSum;
	}

}
