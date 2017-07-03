package com.Email;

import java.util.ArrayList;

public class EmailInfo {
	
	public String title;
	public String sender;
	public ArrayList<String> receiver=new ArrayList<String>();
	public String body;
	public boolean isText;
	
	public String getTitle()
	{
		return this.title;
	}
	public String getSender()
	{
		return this.sender;
	}
	public ArrayList<String> getReceiver()
	{
		return this.receiver;
	}
	public String getBody(){
		return this.body;
	}
	public boolean getIsText()
	{
		return this.isText;
	}
	
	
	public EmailInfo (){
		
		
	}
	
	public EmailInfo (String Title,String Sender, ArrayList<String> Receiver,String Body,boolean IsText){
		this.title=Title;
		this.sender=Sender;
		this.receiver=Receiver;
		this.body=Body;
		this.isText=IsText;

	}

}
