package com.example.orderapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Connection extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		// This call will result in better error messages if you
		// try to do things in the wrong thread.

		//		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		//				.detectDiskReads().detectDiskWrites().detectNetwork()
		//				.penaltyLog().build());

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connection);

		EditText et = (EditText) findViewById(R.id.RecvdMessage);
		et.setKeyListener(null);
		et = (EditText) findViewById(R.id.error_message_box);
		et.setKeyListener(null);

		// Set up a timer task.  We will use the timer to check the
		// input queue every 500 ms

		TCPReadTimerTask tcp_task = new TCPReadTimerTask();
		Timer tcp_timer = new Timer();
		tcp_timer.schedule(tcp_task, 3000, 500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.connection_menu, menu);    
		return true;
	}

	// Route called when the user presses "connect"

	public void openSocket(View view) {
		ConnectionApplication app = (ConnectionApplication) getApplication();
		TextView msgbox = (TextView) findViewById(R.id.error_message_box);

		// Make sure the socket is not already opened 

		if (app.sock != null && app.sock.isConnected() && !app.sock.isClosed()) {
			msgbox.setText("Socket already open");
			return;
		}

		// open the socket.  SocketConnect is a new subclass
		// (defined below).  This creates an instance of the subclass
		// and executes the code in it.

		new SocketConnect().execute((Void) null);
	}




	public void sendMessage(View view) {
		ConnectionApplication app = (ConnectionApplication) getApplication();

		// Get the message from the box

		//EditText et = (EditText) findViewById(R.id.MessageText);
		//String msg = et.getText().toString();
		String msg = ReviewPage.OrderString();
	    

		EditText clientET = (EditText)findViewById(R.id.clientid);
		//clientET.setText(chef_ID);
		//Log.i("chef",""+chef_ID);
		int client_to_send = Integer.parseInt(clientET.getText().toString());
		// Create an array of bytes.  First byte will be the
		// message length, and the next ones will be the message

		byte buf[] = new byte[msg.length() + 2];
		buf[0] = (byte)client_to_send; 
		buf[1] = (byte)0; //normal operations
		System.arraycopy(msg.getBytes(), 0, buf, 2, msg.length());

		// Now send through the output stream of the socket

		OutputStream out;
		try {
			out = app.sock.getOutputStream();
			try {
				out.write(buf, 0, msg.length() + 2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}






	//Broadcast empty message to get ChefApp to send back an empty message - To Update Chef client ID
	public void requestChefID() {
		ConnectionApplication app = (ConnectionApplication) getApplication();

		byte buf[] = new byte[2];

		buf[0] = (byte)255;
		buf[1] = (byte)1; 	//special integer specifies chef app needs to reply with empty message to update 
							//the clientID

		// Now send through the output stream of the socket

		OutputStream out;
		try {
			out = app.sock.getOutputStream();
			try {
				out.write(buf, 0, 2); //only writing 2 bytes, 255 and special int since it's a request 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//  Called when the user wants to send a message


//	//Automatic sending
//	public void sendOrder(View view) {
//		ConnectionApplication app = (ConnectionApplication) getApplication();
//
//		String msg = ReviewPage.OrderSum();
//		Log.i("con",""+ app.getChefClientID());
//		byte buf[] = new byte[msg.length() + 2];
//		if(app.getChefClientID() == 0)
//			app.setChefClientID(255);
//		else {
//			buf[0] = (byte)app.getChefClientID(); 
//			buf[1] = (byte)0; // special integer is zero for normal order sends.
//			System.arraycopy(msg.getBytes(), 0, buf, 2, msg.length());
//
//			// Now send through the output stream of the socket
//
//			OutputStream out;
//			try {
//				out = app.sock.getOutputStream();
//				try {
//					out.write(buf, 0, msg.length() + 2);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	
	
	//  Called when the user wants to send a message

	public void sendOrder(View view) {
		
		
		ConnectionApplication app = (ConnectionApplication) getApplication();

		
		//String msg = ReviewPage.OrderString();
		String msg = buildToSend();
		//NumberCustomers.CurrentCust.customerSum="";
		
		byte buf[] = new byte[msg.length() + 2];
		if(app.getChefClientID() == 0)
			app.setChefClientID(255);
		else {
			buf[0] = (byte)app.getChefClientID(); 
			buf[1] = (byte)0; // special integer is zero for normal order sends.
			System.arraycopy(msg.getBytes(), 0, buf, 2, msg.length());

			// Now send through the output stream of the socket

			OutputStream out;
			try {
				out = app.sock.getOutputStream();
				try {
					out.write(buf, 0, msg.length() + 2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	// Called when the user closes a socket

	public void closeSocket(View view) {
		ConnectionApplication app = (ConnectionApplication) getApplication();
		Socket s = app.sock;
		try {
			s.getOutputStream().close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String buildToSend(){
		String message = "";
		for(int i=0; i<WelcomePage.getNumCust(); i++)
		{
			message = message + WelcomePage.CustArray[i].alcohol[0] + "/" + WelcomePage.CustArray[i].alcohol[1] + "/" + WelcomePage.CustArray[i].alcohol[2] + "/" +WelcomePage.CustArray[i].alcohol[3] + "/" +
					            WelcomePage.CustArray[i].mainmenu[0] + "/" + WelcomePage.CustArray[i].mainmenu[1] + "/" + WelcomePage.CustArray[i].mainmenu[2] + "/" + WelcomePage.CustArray[i].mainmenu[3] + "/" +
					            WelcomePage.CustArray[i].appetizer[0] + "/" + WelcomePage.CustArray[i].appetizer[1] + "/" + WelcomePage.CustArray[i].appetizer[2]+ "/" +
					            WelcomePage.CustArray[i].special[0] + "/" +WelcomePage.CustArray[i].special[1]+"*";
		}
		return message;
	}

	// Construct an IP address from the four boxes

	public String getConnectToIP() {
		String addr = "";
		EditText text_ip;
		text_ip = (EditText) findViewById(R.id.ip1);
		addr += text_ip.getText().toString();
		text_ip = (EditText) findViewById(R.id.ip2);
		addr += "." + text_ip.getText().toString();
		text_ip = (EditText) findViewById(R.id.ip3);
		addr += "." + text_ip.getText().toString();
		text_ip = (EditText) findViewById(R.id.ip4);
		addr += "." + text_ip.getText().toString();
		return addr;
	}

	// Gets the Port from the appropriate field.

	public Integer getConnectToPort() {
		Integer port;
		EditText text_port;

		text_port = (EditText) findViewById(R.id.port);
		port = Integer.parseInt(text_port.getText().toString());

		return port;
	}


	// This is the Socket Connect asynchronous thread.  Opening a socket
	// has to be done in an Asynchronous thread in Android.  Be sure you
	// have done the Asynchronous Tread tutorial before trying to understand
	// this code.

	public class SocketConnect extends AsyncTask<Void, Void, Socket> {

		// The main parcel of work for this thread.  Opens a socket
		// to connect to the specified IP.

		protected Socket doInBackground(Void... voids) {
			Socket s = null;
			String ip = getConnectToIP();
			Integer port = getConnectToPort();

			try {
				s = new Socket(ip, port);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s;
		}

		// After executing the doInBackground method, this is 
		// automatically called, in the UI (main) thread to store
		// the socket in this app's persistent storage

		protected void onPostExecute(Socket s) {
			ConnectionApplication myApp = (ConnectionApplication) Connection.this
					.getApplication();
			myApp.sock = s;

			
			byte buf[] = new byte[2];

			buf[0] = (byte)255;
			buf[1] = (byte)1; 	//special integer specifies chef app needs to reply with empty message to update 
								//the clientID

			// Now send through the output stream of the socket

			OutputStream out;
			try {
				out = myApp.sock.getOutputStream();
				try {
					out.write(buf, 0, 2); //only writing 2 bytes, 255 and special int since it's a request 
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// This is a timer Task.  Be sure to work through the tutorials
	// on Timer Tasks before trying to understand this code.

public class TCPReadTimerTask extends TimerTask {
		
		public void run() {
			
			//UI
//			SpecialsPage.counter++;
//			SpecialsPage.mHandler.obtainMessage(1).sendToTarget();
//			
			ConnectionApplication app = (ConnectionApplication) getApplication();
			if (app.sock != null && app.sock.isConnected()
					&& !app.sock.isClosed()) {

				try {
					InputStream in = app.sock.getInputStream();

					// See if any bytes are available from the Middleman

					int bytes_avail = in.available();
					if (bytes_avail > 0) {

						// If so, read them in and create a string

						byte buf[] = new byte[bytes_avail];
						in.read(buf);

						Log.i("con",""+app.getChefClientID()); // Debugging 
						int specialInt = buf[1];
						Log.i("update",""+specialInt); // Debugging
						// if the special integer is 1 or 2 it means the chef client ID is being 
						// updated and we don't need to read the string.
						if(specialInt == 0) {

							final String s = new String(buf, 2, bytes_avail-2, "US-ASCII");						
							Log.i("Update",s);
							// As explained in the tutorials, the GUI can not be
							// updated in an asynchronous task.  So, update the GUI
							// using the UI thread.

							runOnUiThread(new Runnable() {
								public void run() {
									EditText et = (EditText) findViewById(R.id.RecvdMessage);
									et.setText(s);
								}
							});
						} 
						//If the special integer is 2 the chef side is updating the client ID, set it here
						else if(specialInt == 2) {
							int chefClientID = buf[0];
							app.setChefClientID(chefClientID);
						}
						// If the special integer is 4 the DE2 is sending updated specials from the SD card
						else if (specialInt == 4) {
							
							final String s = new String(buf, 2, bytes_avail-2, "US-ASCII");
							Log.i("Update",s);
							SpecialsPage.stringForUpdateSpecials = s;
							//SpecialsPage.specialItems.add(s);
							SpecialsPage.mHandler.obtainMessage(1).sendToTarget();

						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 	 
		}	
	}


	//	@Override
	//	public void onBackPressed() {
	//	}
}

