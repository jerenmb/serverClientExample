package com.example.serversandbox;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import android.widget.TextView;
import android.widget.Toast;

public class ServerRunnable implements Runnable {

	CommunicationBean aBean;
	JSONInputStream inFromServer;
	JSONOutputStream outToServer;
	TextView responseView;
	
	public ServerRunnable(CommunicationBean aBean, /*JSONInputStream inFromServer, JSONOutputStream outToServer,*/ TextView responseView) {
		this.aBean = aBean;
		//this.inFromServer = inFromServer;
		//this.outToServer = outToServer;
		this.responseView = responseView;
	}
	
	public void run(){
		try {
			Socket toServer = new Socket("10.0.2.2", 9292);
			//setup the JSON streams to be used later.
	        final JSONInputStream inFromServer = 
            new JSONInputStream(toServer.getInputStream());
	        final JSONOutputStream outToServer = 
            new JSONOutputStream(toServer.getOutputStream());
				System.out.println("Thread id: "
	                +Thread.currentThread().getName());
				try {
	                //send the bean
	                outToServer.writeObject(aBean);
					HashMap aMap = (HashMap)inFromServer.readObject();
	                
	                //Toast.makeText(null, aMap.toString(), Toast.LENGTH_LONG).show();
					//responseView.setText(aMap.toString());
	            } catch (org.quickconnectfamily.json.JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*for (int i = 0; i < 3; i++) {
			System.out.println("Thread id: "
                +Thread.currentThread().getName());
			try {
                //send the bean
                outToServer.writeObject(aBean);
				HashMap aMap = (HashMap)inFromServer.readObject();
                
                responseView.setText(aMap.toString());
            } catch (org.quickconnectfamily.json.JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}