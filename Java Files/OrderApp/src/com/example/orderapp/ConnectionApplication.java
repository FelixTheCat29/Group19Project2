package com.example.orderapp;

import java.net.Socket;

import android.app.Application;

public class ConnectionApplication extends Application {
	Socket sock = null;
	
	private int chefID=0;  //Init to 0 meaning has no client id yet, needs to receive Chef App broadcast.
	
	public int getChefClientID() {
		return chefID;
	}

	public void setChefClientID(int client_id) {
		this.chefID = client_id;
	}
	
}

