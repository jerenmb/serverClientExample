package org.quickconnectfamily.json;


import java.util.ArrayList;

public class CommunicationBean {
	String command;
	ArrayList aList = new ArrayList();
	
	public CommunicationBean(String string, ArrayList arrayList) {
		this.command = string;
		this.aList = arrayList;
	}

	public CommunicationBean() {
		
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setData(ArrayList newList) {
		this.aList = newList;
	}
	
}
