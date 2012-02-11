package com.talker.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmergencyEmail {

	public static void main(String[]arg) throws AddressException, MessagingException, FileNotFoundException, IOException{
		EmergencyEmail mailFlood = new EmergencyEmail();
		mailFlood.sendEmergencyEmail();
	}

	public void sendEmergencyEmail() {
		Properties props = System.getProperties();
		Properties chatterboxProperties = new Properties();
		try {
			chatterboxProperties.load(new FileInputStream(new File("myproperties.properties")));
			props.put("mail.smtp.host", chatterboxProperties.getProperty("email.account.host"));
			props.put("mail.smtp.user", chatterboxProperties.getProperty("email.account.user"));
			props.put("mail.smtp.password", chatterboxProperties.getProperty("email.account.password"));
			props.put("mail.smtp.port", chatterboxProperties.getProperty("email.account.port"));
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(chatterboxProperties.getProperty("email.account.user")));
			String[] toAddresses = chatterboxProperties.getProperty("email.contacts").split(",");
			InternetAddress[] to_address = new InternetAddress[toAddresses.length];
			for(int i=0;i<toAddresses.length;i++){
				to_address[i] = new InternetAddress(toAddresses[i]);
				message.addRecipient(Message.RecipientType.TO, to_address[i]);
			}
			message.setSubject(chatterboxProperties.getProperty("email.subject"));
			message.setText(chatterboxProperties.getProperty("email.body"));
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", chatterboxProperties.getProperty("email.account.user"), chatterboxProperties.getProperty("email.account.password"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
