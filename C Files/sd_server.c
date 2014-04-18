/* EECE 381 Group 19 Module 2 Project */
/* Members: Alara Ozturk, Kong Cheng, John Jiang, Ryan Kwon, Janice Zhang */
/* Purpose: Display specials of the day from SD card on VGA and receive and send data to and from
 * 			Middleman for communication with Android devices. */



#include "sd_server.h"

//*********************************************** MAIN **************************************************
int main()
{
	InitSDCard();
	initCharBuffer();
	initPixelBuffer();
	initUART();


	//  **************************************** Linked list and String parsing testing ****************************************

	head=NULL; //head is a global variable of type struct table* declared right after the struct definition in sd_server.h
	numMenuItems = 13;

	//char * testStr = "1/2/2/3/2/4*";
	//char * testStr2 = "0/2/3/4/11/2222*222/3/1/0/0/0*";
	//char * testStr3 = "1/2/0/0/2/4/0/0/1*5/6*";
	//char * testStr4 = "0/0/0/0/0/0/0/0/0/2/3/5*1/2/3*";

	//addToList(1, testStr); //table1 order all together
	//addToList(2, testStr2);//tale2 order all together, customers not seperated
	//addToList(3, testStr3);
	//addToList(4, testStr4);
	//printListInConsole();
	//itemDisplay();

	//*********************************************** SD CARD CODE **************************************************

	char buffer[128] = "WELCOME TO Heaven!!\r\n\0";
	int i;
	short int sd_fileh = alt_up_sd_card_fopen("special.txt", 0);
	if(sd_fileh == -1)
		sd_fileh = alt_up_sd_card_fopen("special.txt", 1);

	if (sd_fileh < 0)
		printf("Problem creating file. Error %i", sd_fileh);
	else
	{
		printf("SD Accessed Successfully, writing data...");

		int index = 0;

		while (buffer[index] != '\0')
		{
			printf("%c",buffer[index]);
			alt_up_sd_card_write(sd_fileh, buffer[index]);
			index = index + 1;
		}
		printf("Done!\n");

		printf("Closing File...");
		alt_up_sd_card_fclose(sd_fileh);

		sd_fileh = getFileHandler("special.txt");
		readFile(sd_fileh);
	}

	//*********************************************** SERVER CODE **************************************************
	unsigned char data;
	unsigned char parity;

	char* msg = malloc(128);
	int client_id=0;
	int num_to_receive=0;
	int client_to_send = 0;
	int specialInt;
	int clearscreen=0;

	char* line = malloc(128);

	while(1){
		printf("Clearing read buffer to start\n");
		while (alt_up_rs232_get_used_space_in_read_FIFO(uart)) {
			alt_up_rs232_read_data(uart, &data, &parity);
		}
		// Now receive the message from the Middleman
		printf("Waiting for data to come back from the Middleman\n\n");
		client_id = readIntegerFromUART(data,parity);  // First byte is client ID of sender
		num_to_receive = readIntegerFromUART(data,parity); // Second byte is the number of characters in our message
		client_to_send = readIntegerFromUART(data,parity); // Third byte is client ID of receiver
		specialInt = readIntegerFromUART(data,parity); // Fourth byte is special integer
		printf("Client ID: %d, %x\n",client_id, client_id);
		printf("About to receive %d characters:\n", num_to_receive);
		printf("Client to send: %d, %x\n", client_to_send, client_to_send);
		printf("Special Integer: %d \n",specialInt);
		for (i = 0; i < num_to_receive-2; i++) { // The num_to_receive is 2 too many because of client ID and specialInt
			while (alt_up_rs232_get_used_space_in_read_FIFO(uart) == 0);
			alt_up_rs232_read_data(uart, &data, &parity);
			printf("%c", data);
			sprintf(msg+i, "%c", data);
		}
		printf("\n");
		sprintf(msg+i, "%c", '\0');
		//printf("\n%s\n", msg);
		//printf("Str length = %d \n",strlen(msg));


		//************************************* END OF RECEIVING **********************************************

		if(specialInt == 0) {

			clearscreen=0;
			addToList(client_id, msg);
			printListInConsole();
			itemDisplay(clearscreen); //calls the vga function for display on the vga

		}
		else if(specialInt==5)
		{
			deleteThisTableFromList(client_to_send);
			printf("Client id : %d \n",client_to_send);
			clearscreen = 1;
			printListInConsole();

			itemDisplay(clearscreen);

		}
		else if(specialInt == 3 ) {
			//sprintf(msg+i, "%c", '\0');
			sd_fileh = getFileHandler("special.txt");
			parseSpecials(msg);
			writeLineToFile( sd_fileh, msg);
			//parseSpecials(msg);

		}
		else if(specialInt == 4) {


			sd_fileh = getFileHandler("special.txt");
			readFileToLine(sd_fileh,line);


			printf("\nline: %s\n",line);
			printf("Str length: %d\n", strlen(line));
			printf("Send line to the Middleman\n");
			unsigned char* buf = malloc(strlen(line)+4); // 1 byte each for receiver ID, length, sender ID, and specialInt
			buf[0] = (unsigned char)client_id;
			buf[1] = (unsigned char)(strlen(line)+2); //1 byte for the sender Client ID, 1 byte for specialInt
			buf[2] = (unsigned char)client_id;
			buf[3] = (unsigned char)4;

			for(i=0;i<strlen(line);i++){
				buf[i+4] = line[i];
			}
			alt_up_rs232_write_data(uart, (unsigned char) buf[0]);
			alt_up_rs232_write_data(uart, (unsigned char) buf[1]);
			alt_up_rs232_write_data(uart, (unsigned char) buf[2]);
			alt_up_rs232_write_data(uart, (unsigned char) buf[3]);

			// Now send the actual message to the Middleman
			for (i = 0; i < strlen(line); i++) {
				alt_up_rs232_write_data(uart, buf[i+4]);
			}
			free(buf);
			continue;
		}

		printf("Sending the message to the Middleman\n");
		unsigned char* buf = malloc(strlen(msg)+4); // 1 byte each for receiver ID, length, sender ID, and specialInt
		buf[0] = (unsigned char)client_to_send;
		buf[1] = (unsigned char)(strlen(msg)+2); //1 byte for the sender Client ID, 1 byte for specialInt
		buf[2] = (unsigned char)client_id;
		buf[3] = (unsigned char)specialInt;

		for(i=0;i<strlen(msg);i++){
			buf[i+4] = msg[i];
		}
		alt_up_rs232_write_data(uart, (unsigned char) buf[0]);
		alt_up_rs232_write_data(uart, (unsigned char) buf[1]);
		alt_up_rs232_write_data(uart, (unsigned char) buf[2]);
		alt_up_rs232_write_data(uart, (unsigned char) buf[3]);

		// Now send the actual message to the Middleman
		for (i = 0; i < strlen(msg); i++) {
			alt_up_rs232_write_data(uart, buf[i+4]);
		}
		free(buf);

	}
	return 0;
}
//END OF MAIN//


//*********************************************** UART FUNCTIONS **************************************************


/* Read a byte from UART and cast it as an integer. Returns the integer.*/
int readIntegerFromUART(unsigned char data, unsigned char parity){
	while (alt_up_rs232_get_used_space_in_read_FIFO(uart) == 0);
	alt_up_rs232_read_data(uart, &data, &parity);
	return (int)data;
}

/* Initialize the UART RS-232 Connection */
void initUART() {
	uart = alt_up_rs232_open_dev( "/dev/rs232_0" );
	if(uart == NULL)
		printf("UART did not initialize properly.\n");
	else
		printf("Successful UART Initialization.\n");
}

//*********************************************** VGA FUNCTIONS **************************************************

//Reads the message as a string, divides it into sections and prints on the vga screen
void showOnVGAScreen(char *order, int x, int y)
{
	int x_cor, y_cor;

	x_cor=x;
	y_cor=y;

	// Dividing the screen//
	alt_up_pixel_buffer_dma_draw_line(pixel_buffer, 80, 0, 80, 240,  0x99FF, 0); // Make the color black later on
	alt_up_pixel_buffer_dma_draw_line(pixel_buffer, 160, 0, 160, 240, 0x99FF, 0);
	alt_up_pixel_buffer_dma_draw_line(pixel_buffer, 240, 0, 240, 240, 0x99FF, 0);


	alt_up_char_buffer_string(char_buffer, order, x_cor, y_cor);


	//alt_up_char_buffer_string(char_buffer, "Order #1", 5, 2);
	//alt_up_char_buffer_string(char_buffer, order, 27, i);  //prints the order received by the customer on the vga-screen in given coord
	//alt_up_char_buffer_string(char_buffer, "Order #3", 47, 2);
	//alt_up_char_buffer_string(char_buffer, "Order #4", 67, 2);

}

/* Initialize the Pixel Buffer and Clear VGA screen */
void initPixelBuffer(){
	pixel_buffer =	alt_up_pixel_buffer_dma_open_dev("/dev/video_pixel_buffer_dma_0");
	alt_up_pixel_buffer_dma_change_back_buffer_address(pixel_buffer,	PIXEL_BUFFER_BASE);
	alt_up_pixel_buffer_dma_swap_buffers(pixel_buffer);
	while(alt_up_pixel_buffer_dma_check_swap_buffers_status(pixel_buffer));
	// Clear the screen
	alt_up_pixel_buffer_dma_clear_screen(pixel_buffer, 0);
}

/* Initialize and Clear the Character Buffer */
void initCharBuffer(){
	char_buffer = alt_up_char_buffer_open_dev("/dev/video_character_buffer_with_dma_0");
	if ( char_buffer == NULL)
		alt_printf ("Error: could not open char buffer\n");
	else
		alt_printf ("Opened char buffer\n");
	alt_up_char_buffer_init(char_buffer);
	alt_up_char_buffer_clear(char_buffer);
}

//*********************************************** SD CARD FUNCTIONS **************************************************

/* Write line to sdCard file identified by fileHandle*/
void writeLineToFile(short int fileHandle, char* line) {

	printf("Writing message to SD Card\n");
	printf("message: %s", line);
	int index = 0;
	while (line[index] != '\0')
	{
		alt_up_sd_card_write(fileHandle, line[index]);
		index = index + 1;
	}

	alt_up_sd_card_write(fileHandle, line[index]); //write the null character

	printf("Done, closing File...");
	alt_up_sd_card_fclose(fileHandle);
	printf("File closed.\n");

	return;
}


/* Returns fileHandler for file name*/
short int getFileHandler(char* fileName) {
	short int fileHandle;
	fileHandle = alt_up_sd_card_fopen(fileName,0);
	return fileHandle;
}


/* Reads the entire file to the character buffer (parameter "line") assume that line is big enough to fit
 * the entire file */
void readFileToLine(short int fileHandle, char* line){

	short int ascii;
	ascii = alt_up_sd_card_read(fileHandle);
	int i=0;
	while(ascii != -1 ) {
		printf("%c",ascii);
		sprintf(line+i, "%c", ascii);
		i++;
		ascii = alt_up_sd_card_read(fileHandle);
	}
	sprintf(line+i, "%c", '\0');
	alt_up_sd_card_fclose(fileHandle);
	printf("End of File \n");
}


/* Read entire file, print to Console */
void readFile(short int fileHandle) {
	printf("inside readfile\n");
	short int ascii;
	ascii = alt_up_sd_card_read(fileHandle);

	while(ascii != -1) {
		printf("%c",ascii);
		ascii = alt_up_sd_card_read(fileHandle);
	}
	alt_up_sd_card_fclose(fileHandle);
	printf("End of file \n");
}


/* Initialize SD card device and print all sdCard file names */
void InitSDCard(void){

	sd_card = alt_up_sd_card_open_dev("/dev/Altera_UP_SD_Card_Avalon_Interface_0");

	if (sd_card == NULL)
		printf("SD Card did not initialize\n");
	else
		printf("SD Card was successfully initialized\n");

	if (alt_up_sd_card_is_Present())
		printf("SD card detected\n");
	else
		printf("No SD Card present \n");

	if(alt_up_sd_card_is_FAT16())
		printf("FAT16 file system detected.\n");
	else
		printf("File system is NOT FAT16 \n");
	return;
}
