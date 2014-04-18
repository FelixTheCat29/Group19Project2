/*
 * linkedlist.c
 *
 *  Created on: Apr 7, 2014
 *      Author: User
 */

#include "sd_server.h"

//Add the order and client to a new node
void addToList(int clientID, char* order)
{
	struct table *temp;
	temp=head;
	var=(struct table *)malloc(sizeof (struct table));
	var->tableID=clientID;
	var->order=order;
	if(globalSpecialItem1 != NULL) {
		strncpy(var->specialItem1,globalSpecialItem1, strlen(globalSpecialItem1));
		var->specialItem1[strlen(globalSpecialItem1)] =  '\0';
		printf("global special item1: %s \n",globalSpecialItem1 );
		printf("special item1: %s \n",var->specialItem1 );
	}
	if(globalSpecialItem2 != NULL) {
		strncpy(var->specialItem2,globalSpecialItem2, strlen(globalSpecialItem2));
		var->specialItem2[strlen(globalSpecialItem2)] =  '\0';
		printf("global special item1: %s \n",globalSpecialItem2 );
		printf("special item1: %s \n",var->specialItem2 );
	}
	parseStringWithStar(var); //this function parses the order string into array of ints

	if(head==NULL)
	{
		head=var;
		head->next=NULL;
	}
	else
	{
		while(temp->next!=NULL)
		{
			temp=temp->next;
		}
		var->next=NULL;
		temp->next=var;
	}
}


// delete the table from the list of table with this table ID
int deleteThisTableFromList(int tableID)
{
	struct table *temp,*var;
	temp=head;
	while(temp!=NULL)
	{
		if(temp->tableID == tableID)
		{
			if(temp==head)
			{
				head=temp->next;
				free(temp);
				return 0;
			}
			else
			{
				var->next=temp->next;
				free(temp);
				return 0;
			}
		}
		else
		{
			var=temp;
			temp=temp->next;
		}
	}
	printf("tableID deleted from list is %d",tableID);
	return 0;
}

void printListInConsole()
{
	int i=0;
	int j=0;
	trav=head;
	if(trav==NULL)
	{
		printf("\nList is Empty");
	}
	else
	{
		printf("\nElements in the List: \n");
		while(trav!=NULL)
		{
			printf(" -> TableID: %d\n",trav->tableID);
			printf("Table order: %s\n",trav->order);
			printf("Table array of qtys: \n");
			for(j=0; j < trav->numOfCustomers ;j++) {
				for(i=0; i<numMenuItems; i++)
					printf("%d, ", trav->qtys[i][j]);
				printf("\n");
			}
			printf("\n");
			trav=trav->next;
		}
		printf("\n");
	}
}

void itemDisplay(int clearscreen)
{
	printf("Clearscreen: %d\n",clearscreen);

	int i=0;
	int k, j;
	int coordx[4]= {2, 22, 42, 62};
	int coord[4]= {15, 35, 55, 75};
	int cord[4]= {5, 25, 45, 65};
	int cordtn[4]= {13, 23, 43, 63};
	int orders[4];
	int y=2;
	int heady=0;
	char tablenum[20];
	char count[20];

	trav=head;

	if(clearscreen == 1)

		alt_up_char_buffer_clear(char_buffer);

	if (trav==NULL)
	{
		printf("Empty list\n");
		alt_up_char_buffer_clear(char_buffer);
	}
	else

		while(trav!=NULL && i<4) //table number
				{
					trav->quadrant=i;

					for(k=0; k<(trav->numOfCustomers);k++)//goes through the customers, next customer comes when the order of first one is finished
					{
						for(j=0; j<numMenuItems;j++)//goes through the order of each customer
						{
							if((trav->qtys[j][k])!= 0)//when an item is ordered, the array entry for that item will be an int not= 0
							{
								//hello ;-) y coordinate starts from 2 for every table, x coordinates are stored in an array and goes to the next start point as the table changes
								sprintf(tablenum,"%d",(trav->tableID));
								sprintf(count,"%d",(trav->qtys[j][k]));
								showOnVGAScreen(getItemNameFromIndex(j, trav),coordx[i],y);//name of the item ordered
								showOnVGAScreen(count,coord[i],y); //how many of each item is ordered
								showOnVGAScreen("Table ",cord[i],heady);
								showOnVGAScreen(tablenum,cordtn[i],heady);
								y++;
							}
						}

					}
					trav=trav->next;
					i++;
					y=2;
				}
}

	/*while(trav!=NULL && i<4) //table number
		{
			trav->quadrant=i; //quadrant is the section on VGA which is allocated by the table


			for(k=0; k<(trav->numOfCustomers);k++)//for every menu item goes through the customers    //goes through the customers, next customer comes when the order of first one is finished
			{
				for(j=0; j<numMenuItems;j++) //goes through the order of each customer
				{
					//if((trav->qtys[j][k])!= 0) //when an item is ordered, the array entry for that item will be an int not= 0
					//{
						for(z=0; z<trav->numOfCustomers;z++)
						{
							orders[z]= trav-> qtys[j][k];

							while(orders[z]!= 0)
							{
								total= orders[z]+orders[z+1];
							}
						}
//y coordinate starts from 2 for every table, x coordinates are stored in an array and goes to the next start point as the table changes
						sprintf(count,"%d",total); // how many are ordered
						showOnVGAScreen(getItemNameFromIndex(j, trav),coordx[i],y);//name of the item ordered
						showOnVGAScreen(total,coord[i],y); //how many of each item is ordered
						y++;
					}
				}

			trav=trav->next;
			i++;
			y=2;
}*/



///*
// * linkedlist.c
// *
// *  Created on: Apr 7, 2014
// *      Author: User
// */
//
//#include "sd_server.h"
//
////Add the order and client to a new node
//void addToList(int clientID, char* order)
//{
//	struct table *temp;
//	temp=head;
//	var=(struct table *)malloc(sizeof (struct table));
//	var->tableID=clientID;
//	var->order=order;
//	if(globalSpecialItem1 != NULL) {
//		strncpy(var->specialItem1,globalSpecialItem1, strlen(globalSpecialItem1));
//		var->specialItem1[strlen(globalSpecialItem1)] =  '\0';
//		printf("global special item1: %s \n",globalSpecialItem1 );
//		printf("special item1: %s \n",var->specialItem1 );
//	}
//	if(globalSpecialItem2 != NULL) {
//		strncpy(var->specialItem2,globalSpecialItem2, strlen(globalSpecialItem2));
//		var->specialItem2[strlen(globalSpecialItem2)] =  '\0';
//		printf("global special item1: %s \n",globalSpecialItem2 );
//		printf("special item1: %s \n",var->specialItem2 );
//	}
//	parseStringWithStar(var); //this function parses the order string into array of ints
//
//	if(head==NULL)
//	{
//		head=var;
//		head->next=NULL;
//	}
//	else
//	{
//		while(temp->next!=NULL)
//		{
//			temp=temp->next;
//		}
//		var->next=NULL;
//		temp->next=var;
//	}
//}
//
//
//// delete the table from the list of table with this table ID
//int deleteThisTableFromList(int tableID)
//{
//	struct table *temp,*var;
//	temp=head;
//	while(temp!=NULL)
//	{
//		if(temp->tableID == tableID)
//		{
//			if(temp==head)
//			{
//				head=temp->next;
//				free(temp);
//				return 0;
//			}
//			else
//			{
//				var->next=temp->next;
//				free(temp);
//				return 0;
//			}
//		}
//		else
//		{
//			var=temp;
//			temp=temp->next;
//		}
//	}
//	printf("tableID deleted from list is %d",tableID);
//	return 0;
//}
//
//void printListInConsole()
//{
//	int i=0;
//	int j=0;
//	trav=head;
//	if(trav==NULL)
//	{
//		printf("\nList is Empty");
//	}
//	else
//	{
//		printf("\nElements in the List: \n");
//		while(trav!=NULL)
//		{
//			printf(" -> TableID: %d\n",trav->tableID);
//			printf("Table order: %s\n",trav->order);
//			printf("Table array of qtys: \n");
//			for(j=0; j < trav->numOfCustomers ;j++) {
//				for(i=0; i<numMenuItems; i++)
//					printf("%d, ", trav->qtys[i][j]);
//				printf("\n");
//			}
//			printf("\n");
//			trav=trav->next;
//		}
//		printf("\n");
//	}
//}
//
//void itemDisplay(int clearscreen)
//{
//	printf("Clearscreen: %d\n",clearscreen);
//	int i=0;
//	int a=0;
//	int k, j;
//	int coordx[4]= {2, 22, 42, 62};
//	int coord[4]= {15, 35, 55, 75};
//	int y=2;
//	char count[20];
//
//	trav=head;
//	if(clearscreen == 1)
//		alt_up_char_buffer_clear(char_buffer);
//
//	if (trav==NULL)
//	{
//		printf("Empty list\n");
//		alt_up_char_buffer_clear(char_buffer);
//	}
//	else
//		while(trav!=NULL && i<4) //table number
//		{
//			trav->quadrant=i;
//			for(k=0; k<(trav->numOfCustomers);k++)//goes through the customers, next customer comes when the order of first one is finished
//			{
//				for(j=0; j<numMenuItems;j++)//goes through the order of each customer
//				{
//					if((trav->qtys[j][k])!= 0)//when an item is ordered, the array entry for that item will be an int not= 0
//					{
//						//y coordinate starts from 2 for every table, x coordinates are stored in an array and goes to the next start point as the table changes
//						sprintf(count,"%d",(trav->qtys[j][k]));
//						showOnVGAScreen(getItemNameFromIndex(j, trav),coordx[i],y);//name of the item ordered
//						showOnVGAScreen(count,coord[i],y); //how many of each item is ordered
//						y++;
//
//					}
//				}
//			}
//			trav=trav->next;
//			i++;
//			y=2;
//		}
//}

