package service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;
public class ReadMailSample  {
StringBuilder sb=new StringBuilder();
    ArrayList al=new ArrayList();
	ArrayList mailContents=new ArrayList();
	Properties properties = null;
	private Session session = null;
	private Store store = null;
	private Folder inbox = null;
	private String userName ;// provide user name
	private String password ;
	public String readMails(String emailId,String Pass)
	{
            System.out.println("user Email===="+emailId+"password===="+Pass);
		 userName=emailId;
		 password=Pass;
		 properties = new Properties();
		 properties.setProperty("mail.host", "imap.gmail.com");
		 properties.setProperty("mail.port", "995");
		 properties.setProperty("mail.transport.protocol", "imaps");
		 session = Session.getInstance(properties,
		 new javax.mail.Authenticator() {
		 protected PasswordAuthentication getPasswordAuthentication() {
		 return new PasswordAuthentication(emailId, Pass);
		 }
		 });

try {
store = session.getStore("imaps");
store.connect();
inbox = store.getFolder("INBOX");
inbox.open(Folder.READ_ONLY);
Message messages[] = inbox.search(new FlagTerm(
new Flags(Flag.SEEN), false));

System.out.println("Number of mails = " + messages.length);
sb.append("no of unread mails are"+messages.length+"\n");
for (int i = 0; i < messages.length; i++) 
{
    Message message = messages[i];
 Address[] from = message.getFrom();
// System.out.println("-------------------------------");
// System.out.println("Date : " + message.getSentDate());
// mailContents.add(message.getSentDate().toString());
// System.out.println("From : " + from[0]);
 mailContents.add(from[0]);
// System.out.println("Subject: " + message.getSubject()); 
 mailContents.add(message.getSubject());
// System.out.println("Content :");
sb.append("from:"+from[0].toString()+"\n");
sb.append("subject"+message.getSubject()+"\n");
 processMessageBody(message);
 mailContents.add(message.getContent());
 sb.append("Conntent:"+message.getContent());

// println("===="System.out.+al);
 System.out.println("--------------------------------");
 System.out.println("===="+sb.toString());
}
for(int i=0;i<mailContents.size();i++)
{
//    System.out.println("==="+mailContents.get(i));
}
inbox.close(true);
store.close(); 
System.out.println("allmail===="+al);
}

catch (NoSuchProviderException e)
{ e.printStackTrace(); } 
catch (MessagingException e)
{ e.printStackTrace();
}
catch(Exception e)
{
	e.printStackTrace();
}
return sb.toString(); 
    } 
 public void processMessageBody(Message message)
 {
     try 
     {
         Object content = message.getContent();
   // check for string // then check for multipart 
if (content instanceof String) 
{ 
//    sb.append("content"+content+"\n");
//    System.out.println(content);
} 
else if (content instanceof Multipart) { 
    Multipart multiPart = (Multipart) content; 
    procesMultiPart(multiPart);
}
else if (content instanceof InputStream) {
    InputStream inStream = (InputStream) content;
    int ch; 
//    sb.append("content:");
    while ((ch = inStream.read()) != -1) {
//        System.out.write(ch); 
//        sb.append(ch);
    }
sb.append("\n");
}
 al.add(mailContents);
     } 
     catch (IOException e) { e.printStackTrace(); }
     catch (MessagingException e) { e.printStackTrace(); } }
 public void procesMultiPart(Multipart content)
 { try {
     int multiPartCount = content.getCount();
//     for (int i = 0; i < multiPartCount; i++) 
     { 
         BodyPart bodyPart = content.getBodyPart(0); 
     Object o;
     o = bodyPart.getContent();
     if (o instanceof String) { 
//         System.out.println(o);
         sb.append("Content"+o+"\n");
     }
     else if (o instanceof Multipart) { 
//         procesMultiPart((Multipart) o); 
     }
     }
 } catch (IOException e) { e.printStackTrace(); }
 catch (MessagingException e) { e.printStackTrace(); }
 }
  
		
	public static void main(String[] args)
 { 
     ArrayList one=new ArrayList();
     ArrayList mail=new ArrayList();
     ReadMailSample sample = new ReadMailSample();
  String str= sample.readMails("if.mindsoft.blore@gmail.com","eswar@007"); 
  System.out.println("=======00000"+str);
//     one.add(mail.get(0));
//     System.out.println("first maillll===="+one);
 }	
	

}
