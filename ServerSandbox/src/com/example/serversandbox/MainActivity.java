package com.example.serversandbox;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity{
	int numMessagesSent = 0;
	Handler UIHandler = new Handler();
	WeakReference<TextView> weakResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        
        //get the UI items to interact with
        final TextView responseView = 
        (TextView)this.findViewById(R.id.response);
        final EditText textInput = 
        (EditText)this.findViewById(R.id.username);
        
        /*InternetTask task = new InternetTask(responseView);
        task.execute();
        */

        /*try{
        	//connect to the server
			Socket toServer = new Socket("10.0.2.2", 9292);
	        //setup the JSON streams to be used later.
	        final JSONInputStream inFromServer = 
            new JSONInputStream(toServer.getInputStream());
	        final JSONOutputStream outToServer = 
            new JSONOutputStream(toServer.getOutputStream());
	        */
            //add the on click listener to the button
            Button sendButton = 
            (Button)this.findViewById(R.id.sendButton);
            sendButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
                    numMessagesSent++;
                    //prepare the bean
					ArrayList aDataList = new ArrayList();
                    aDataList.add(numMessagesSent);
                    aDataList.add(textInput.getText().toString());
                    CommunicationBean aBean = new CommunicationBean();
                    aBean.setCommand(textInput.getText().toString());
                    aBean.setData(aDataList);
                    
                    weakResponse = new WeakReference<TextView>(responseView);
                    
                   HandlerRunnable testThread = new HandlerRunnable(UIHandler, weakResponse, aBean);
                   Thread favoriteThread = new Thread(testThread);
                   favoriteThread.start();
                    
                    /*ServerRunnable startThreads = 
                    		new ServerRunnable(aBean, responseView);
                    Thread aThread = new Thread(startThreads);
        			aThread.start();*/
                    
                    /*try {
                        //send the bean
                        outToServer.writeObject(aBean);
						HashMap aMap = (HashMap)inFromServer.readObject();
                        
                        responseView.setText(aMap.toString());
                    } catch (org.quickconnectfamily.json.JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
                }
            });
           /* toServer.close();
        }
        catch(Exception e){
            e.printStackTrace();
            responseView.setText("Error: Unable to communicate with server. "+e.getLocalizedMessage());
        } */
            
           /* Thread thread = new Thread(new Runnable() {
            	
            	@Override
            	public void run(){
            		try {
            			Socket toServer = new Socket("10.0.2.2", 9292);
            			//setup the JSON streams to be used later.
            	        final JSONInputStream inFromServer = 
                        new JSONInputStream(toServer.getInputStream());
            	        final JSONOutputStream outToServer = 
                        new JSONOutputStream(toServer.getOutputStream());
            	        for (int i = 0; i < 3; i++) {
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
            			}
            		} catch (UnknownHostException e1) {
            			// TODO Auto-generated catch block
            			e1.printStackTrace();
            		} catch (IOException e1) {
            			// TODO Auto-generated catch block
            			e1.printStackTrace();
            		}
            	}
            });
            thread.start();*/
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
