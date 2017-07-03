package com.Email;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.las.MachineManagement.Bean.Emailconfiguration;





public class EmailHelper {
	
	public Emailconfiguration emailConfiguration;
	public EmailInfo emailInfo;
	
	
    private final transient Properties properties = System.getProperties();

    private transient Session session;
	
	public EmailHelper(){}
	
	public EmailHelper(Emailconfiguration EmailConfiguration,EmailInfo EmailInfo){
		this.emailConfiguration=EmailConfiguration;
		this.emailInfo=EmailInfo;
	}
	
	public boolean Send() throws AddressException, MessagingException
	{
		boolean result=false;
		
		try
		{
		    
		    properties.setProperty("mail.transport.protocol", "smtp"); 
		    properties.setProperty("mail.smtp.auth", "true");
		    properties.setProperty("mail.smtp.host", emailConfiguration.getServer());
		    properties.setProperty("mail.smtp.port",String.valueOf(emailConfiguration.getPort()));
		    properties.setProperty("username",emailConfiguration.getUserName());
		    properties.setProperty("password",emailConfiguration.getPassWord());
		    
		    session = Session.getInstance(properties);
		    
		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(emailConfiguration.getUserName()));
		    message.setRecipients(RecipientType.TO, getEmailAddressList(emailInfo.getReceiver()));
		    message.setSubject(emailInfo.title);
		    message.setContent(emailInfo.body.toString(), "text/html;charset=utf-8");

	        Transport tran=session.getTransport("smtp");  
	     
	        tran.connect(properties.getProperty("mail.smtp.host"), Integer.parseInt(properties.getProperty("mail.smtp.port")) ,properties.getProperty("username"),properties.getProperty("password"));  
	        
	        tran.sendMessage(message, getEmailAddressList(emailInfo.getReceiver()));
			result=true;
		}
		catch(Exception ex)
		{
			result=false;
			ex.printStackTrace();
		}
		
		return result;
		
	}
	
	
	public InternetAddress[] getEmailAddressList(ArrayList<String> receiver) throws AddressException
	{
		InternetAddress[] addresses=null;
	    final int num = receiver.size();
	    addresses = new InternetAddress[num];
	    for (int i = 0; i < num; i++) {
	        addresses[i] = new InternetAddress(receiver.get(i));
	    }
		return addresses;
	}
	

   
}
