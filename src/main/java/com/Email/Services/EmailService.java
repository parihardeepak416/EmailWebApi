package com.Email.Services;

import java.io.File;
import java.util.Properties;



import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;
import jakarta.mail.internet.MimeMultipart;

@Service
public class EmailService {
	
	
	
	public void sendEmail(String subject, String message, String to, String from)
	{
		//variable for Gmail
		String host="smtp.gmail.com";
		
		//get the System properties
		Properties properties = System.getProperties();
		
		//setting important information to properties object
		//host set
		properties.put("mail.smtp.host", host);
		//port of smtp
		properties.put("mail.smtp.port", 587);
		//enabling ssl
		properties.put("mail.smtp.ssl", true);
		//authentication enabling
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		
		String username="parihardeepak4162";
		String password="brje jpag fpwk nvyo";
		
		//step-1: getting session object
				Session session = Session.getInstance(properties, new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						
//						return new PasswordAuthentication("parihardeepak4162@gmail.com", "Dpparihar@12");
						return new PasswordAuthentication(username,password);
					}
					
				});
		
				//step 2:- compose the message - we can send text, multi-media as well
				
				session.setDebug(true);
				
				MimeMessage mimeMessage = new MimeMessage(session);
				
				try {
					mimeMessage.setFrom(from);
					InternetAddress[] parse = InternetAddress.parse(to , true);
					//this is for single user sending mail
//					mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					mimeMessage.setRecipients(RecipientType.TO, parse);
					mimeMessage.setSubject(subject);
					mimeMessage.setText(message);
					
					//step -3:- sending the message
					Transport.send(mimeMessage);
					System.out.println("MESSAGE SENDED SUCCESSFULLY");
					
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
			
			//sending attachment with mail

			public void sendAttach(String subject, String message, String to, String from) {
				
		String host="smtp.gmail.com";
				
				//get the System properties
				Properties properties = System.getProperties();
				
				//setting important information to properties object
				//host set
				properties.put("mail.smtp.host", host);
				//port of smtp
				properties.put("mail.smtp.port", 465);
				//enabling ssl
				properties.put("mail.smtp.ssl", true);
				//authentication enabling
				properties.put("mail.smtp.auth", true);
				
				//step-1: getting session object
				Session session = Session.getInstance(properties, new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						
						return new PasswordAuthentication("parihardeepak4162@gmail.com", "Dpparihar@12");
					}
					
				});
				
				//step 2:- compose the message - we can send text, multi-media as well
				
				session.setDebug(true);
				
				MimeMessage mimeMessage = new MimeMessage(session);
				
				try {
					mimeMessage.setFrom(from);
					mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					mimeMessage.setSubject(subject);
					
					//set attachment
					String path="C:\\Users\\parih\\Desktop\\images.jfif";
					MimeMultipart mimeMultipart = new MimeMultipart();
					
					
					MimeBodyPart mimeText = new MimeBodyPart();
					MimeBodyPart mimeFile = new MimeBodyPart();
					
					File file=new File(path);
					
					mimeFile.attachFile(file);
					mimeText.setText(message);			
					
					
					mimeMessage.setContent(mimeMultipart);
					
					mimeMultipart.addBodyPart(mimeFile);
					mimeMultipart.addBodyPart(mimeText);
					
					//step -3:- sending the message
					Transport.send(mimeMessage);
					System.out.println("MESSAGE SENDED SUCCESSFULLY");
					
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
}
