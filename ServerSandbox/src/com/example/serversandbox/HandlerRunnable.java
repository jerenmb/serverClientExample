package com.example.serversandbox;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerRunnable implements Runnable{

	Handler UIThreadHandler;
	WeakReference<TextView> weakResponse;
	CommunicationBean aBean;
	HashMap aMap;
	String inputText;
	Context context;
	
	public HandlerRunnable(Handler aHandler, WeakReference<TextView> aWeakReference, CommunicationBean aBean) {
		this.UIThreadHandler = aHandler;
		this.weakResponse = aWeakReference;
		this.aBean = aBean;
	}
	
	public void run(){
		try {
			
			
			Socket toServer = new Socket("10.0.2.2", 9292);
			//setup the JSON streams to be used later.
	        JSONInputStream inFromServer = 
            new JSONInputStream(toServer.getInputStream());
	        JSONOutputStream outToServer = 
            new JSONOutputStream(toServer.getOutputStream());
				System.out.println("Thread id: "
	                +Thread.currentThread().getName());
				try {
	                //send the bean
	                outToServer.writeObject(aBean);
					aMap = (HashMap)inFromServer.readObject();
					
					
	                
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
	UIThreadHandler.post(new Runnable() {
		@Override
		public void run() {

			System.out.println("It worked.");
			System.out.println((ArrayList)aMap.get("aList"));
			
			ArrayList theList = (ArrayList) aMap.get("aList");
			
			Object theValue = theList.get(1);
			
			//inputText = (String)aMap.get("aList[1]");
			//Surround with try/catch to verify that the UI element exists
			weakResponse.get().setText(theValue.toString());
			
			
		}
	});
	}
	
}
