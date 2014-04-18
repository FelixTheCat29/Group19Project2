/*
 * sd_server.h
 * All .c source files include this header file
 */

#ifndef SD_SERVER_H_
#define SD_SERVER_H_

#include <conio.h>
#include "sys/alt_stdio.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <system.h>
#include <altera_up_sd_card_avalon_interface.h>
#include "altera_up_avalon_character_lcd.h"
#include "altera_up_avalon_video_pixel_buffer_dma.h"
#include "altera_up_avalon_video_character_buffer_with_dma.h"
#include "altera_up_avalon_rs232.h"
#include <string.h>
#include <math.h> //For the parser.c file


/* Global Variables */
alt_up_char_buffer_dev *char_buffer;
alt_up_pixel_buffer_dma_dev* pixel_buffer;
alt_up_rs232_dev* uart;
alt_up_sd_card_dev * sd_card;

int numMenuItems; // cannot be greater than 30 or else must change length of array in struct!!!

char globalSpecialItem1[50];
char globalSpecialItem2[50];

/* struct definition */
struct table
{
	int tableID;
	int numOfCustomers; // number of customers at the table, used to print stuff out... we're not dividing the order by customer
	char* order;
	int qtys[30][4]; //30 should be enough.. for now
	struct table *next;
	char specialItem1[50];
	char specialItem2[50];
}*head,*var,*trav;


/* Function Prototypes */

//VGA Display
void initCharBuffer();
void initPixelBuffer();
void showOnVGAScreen(char *order, int x, int y);//, int clearscreen);


//UART
void initUART();
int readIntegerFromUART(unsigned char data, unsigned char parity);

//SD Card
void writeLineToFile(short int fileHandler, char* line);
void readFileToLine(short int fileHandle, char* line);
short int getFileHandler(char* fileName);
void InitSDCard(void);
void readFile(short int fileHandler);

//Linkedlist
void addToList(int clientID, char* order);
int deleteThisTableFromList(int tableID);
void printListInConsole();
void itemDisplay(int clearscreen);

//Parser
int charToInt(char);
int strToInt(char* str);
void parseStringWithStar(struct table* tableToParse);
void parseSpecials(char* specialsString);
char* getItemNameFromIndex(int index, struct table* t);

#endif /* SD_SERVER_H_ */
