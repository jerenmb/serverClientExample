package com.example.serversandbox;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;

import android.os.AsyncTask;
import android.widget.TextView;

public class InternetTask extends AsyncTask<Void, Integer, Void> {

    private WeakReference<TextView> mUpdateView;

    public InternetTask(TextView responseView) {
    	this.mUpdateView = new WeakReference<TextView>(responseView);
	}

	public void LoginTask(TextView view) {
        this.mUpdateView = new WeakReference<TextView>(view);
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            Socket socket = new Socket("10.0.2.2", 9292);
                    InputStream is = socket.getInputStream();

                    byte[] buffer = new byte[25];
                    int read = is.read(buffer);
                    while(read != -1){
                         publishProgress(read);
                         read = is.read(buffer);
                    }

                    is.close();
                    socket.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;

    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        if(mUpdateView.get() != null && values.length > 0){
                     mUpdateView.get().setText(values[0].toString());
                }
    }

}