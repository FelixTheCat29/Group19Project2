package com.example.orderapp;

public class Customer {
	
	int SelCust;
    int alcohol[] ={0,0,0,0};
    int mainmenu[] ={0,0,0,0};
    double Alsum;
    double MainMenuSum;
	double orderSum;
	
public Customer (int i)
{
SelCust= i;
int alcohol[] ={0,0,0,0};
double Alsum;
double MainMenuSum;
}

public void CustMain(int order[])
{
	mainmenu = order;
}


public void CustAl(int order[])
{
	alcohol = order;
}


public void addToTotal(double d)
{
	orderSum = d;
}

}
