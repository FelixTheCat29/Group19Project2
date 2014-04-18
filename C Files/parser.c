/*
 * parser.c
 *
 *  Created on: Apr 7, 2014
 *      Author: User
 */

#include "sd_server.h"


//parse a string with this format:
// "int/int/int/int/int*int/int/int/int/int*"
// '*' separates each customer, there is a '*' at the end of the string
// number of ints for each customer is equal to global variable numMenuItems
// '/' separates between each item quantity
// returns number of stars in the string (i.e. the number of customers)
void parseStringWithStar(struct table* tableToParse) {
	char strArray[128];

	int i=0;
	int numStars=0;

	//Copy the string to an array it's easier to work with
	while(tableToParse->order[i] != '\0') {
		strArray[i] = tableToParse->order[i];
		i++;
	}
	strArray[i] = '\0'; // manually add null character


	//Parsing starts here
	i=0; //reset i to 0, we reuse this variable for counting again.
	char *string = strArray;
	char *token  = strchr(string, '*');

	while (token != NULL)
	{

		numStars++; //number of stars represent number of customers
		printf("Customer #: %d\n", numStars);
		/* String to scan is in string..token */
		*token++ = '\0';
		printf("a = %s\n", string);
		char *token2 = strtok(string, "/");
		while (token2 != NULL)
		{
			printf("b = %s\n", token2);
			tableToParse->qtys[i%numMenuItems][numStars-1] = strToInt(token2);
			printf("index of qty: %d\n", i);
			token2 = strtok(NULL, "/");
			i++;
		}
		string = token;
		token = strchr(string, '*');
	}
	tableToParse->numOfCustomers = numStars;
	printf("total # of customers: %d \n", numStars);
}

// Parse the item names for specials
// The string is received from Chef App in format "item1$price1*item2$price2*"
// Only extract the item names, don't worry about prices

//void parseSpecials(char* specialsString){
//
//	// numMenuItems += numStars;
//	printf("total # of special items: %d \n", numStars);
//
//}

void parseSpecials(char* specialsString){

	char strArray[128];
	int i=0;
	int numStars=0;


	//Copy the string to an array it's easier to work with
	while(specialsString[i] != '\0') {
		strArray[i] = specialsString[i];
		i++;
	}
	strArray[i] = '\0'; // manually add null character


	//Parsing starts here
	i=0; //reset i to 0, we reuse this variable for counting again.
	char *string = strArray;
	char *token  = strchr(string, '*');

	while (token != NULL)
	{
		numStars++; //number of stars represent number of special items
		printf("Item #: %d\n", numStars);
		/* String to scan is in string..token */
		*token++ = '\0';
		printf("a = %s\n", string);
		char *token2 = strtok(string, "$");
		while (token2 != NULL)
		{
			printf("b = %s\n", token2);

			//skip the price by storing every 2nd string
			if(i == 0) {
				//specialMenu[i/2] =token2;
				printf("i = %d , token2 = %s\n", i/2, token2);
				strncpy(globalSpecialItem1, token2, strlen(token2));
				globalSpecialItem1[strlen(token2)] = '\0';
			}else if (i == 2) {
				printf("i = %d , token2 = %s\n", i/2, token2);
				strncpy(globalSpecialItem2, token2,strlen(token2));
				globalSpecialItem2[strlen(token2)] = '\0';
			}
			printf("index of item in array: %d\n", i);
			token2 = strtok(NULL, "$");
			i++;
		}
		string = token;
		token = strchr(string, '*');
	}

	// numMenuItems += numStars;
	printf("total # of special items: %d \n", numStars);

}

//Alcohol [4], MainMenu[4], Appetizer[3], Specials are parsed into an array that can change
char* getItemNameFromIndex(int index, struct table* t) {

	switch(index){
	case 0:
		return "Kokanee";
	case 1:
		return "Chivas";
	case 2:
		return "Budweiser";
	case 3:
		return "Ballantines";
	case 4:
		return "Pizza";
	case 5:
		return "Hamburger";
	case 6:
		return "Sandwich";
	case 7:
		return "French Fries";
	case 8:
		return "Edamame";
	case 9:
		return "Salad";
	case 10:
		return "Cheese";
	case 11:
		return t->specialItem1;
	case 12:
		return t->specialItem2;
	}
}


//Convert multi-digit number e.g. "33" or "29455" to int
int strToInt(char* str) {
	int intToReturn = 0;
	int index =0;
	int base = 10;

	while(str[index] != '\0') {

		int intAtIndex = charToInt(str[index]);
		int positionValue = pow(base,index);
		intToReturn += intAtIndex*positionValue;
		index++;
	}

	return intToReturn;
}

//Convert single-digit number e.g. '3', '2', or '6' to int
//Called by strToInt() function
int charToInt(char c) {
	int retVal = c-'0';
	return retVal;
}
