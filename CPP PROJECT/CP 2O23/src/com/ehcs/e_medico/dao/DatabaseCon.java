package com.ehcs.e_medico.dao; 

import java.sql.Connection;   
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;



public class DatabaseCon {

	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "system";
	private static String pass = "root";
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	PreparedStatement stm = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	String sql;
	/*create table signup(NAME VARCHAR2(70),EMAIL VARCHAR2(30),ADDRESS VARCHAR2(70),PASSWORD VARCHAR2(10));
select * from signup
drop table signup
*/

	String to ;
	ArrayList em = new ArrayList();
	public int insertIntoDB (ArrayList a)
	{	
		int a1 = 0;
		
		try {

			Class.forName(driver);

			con = DriverManager.getConnection(url, user, pass);

			stmt = con.createStatement();
			 sql = "insert into signup values(? , ? , ? , ? )";
			 stm = con.prepareStatement(sql);
			 
			 stm.setString(1, (String) a.get(0));
			 stm.setString(2, (String) a.get(1));
			 stm.setString(3, (String) a.get(2));
			 stm.setString(4, (String) a.get(3));
			 a1 = stm.executeUpdate();
			
		} catch (Exception e1) {

		}
		return a1;
	}
	
	public int login ( String id , String pass )
	{	
		String pass11 = null;
		String name = null ;
		String email = null;
		String addres =null;
		int b = 0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , user , "root");
			stmt = con.createStatement();
			sql = "select * from signup where EMAIL = ? ";
			stm = con.prepareStatement(sql);
			stm.setString(1, id);
			rs = stm.executeQuery();
			
			if(	rs.next()==false)
			{	
				b = 1;
			}
			else
			{
				do
				{
					pass11 = rs.getString(4);
					name = rs.getString(1);
					email=rs.getString(2);
					//addres=rs.getString(3);
				}while(rs.next());
			}
			
//			Cart ems = new Cart();
//			ems.sendEmail1(email);
			if(pass.equals(pass11) && id.equals(email))
			{
				
				JOptionPane.showMessageDialog(null, "Welcome "+name);
				System.out.println(email+ addres+ name);
			}
			else {
				b = 2;
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException i) {
			i.printStackTrace();
		} catch (Exception u) {
			u.printStackTrace();
		}	
//		email = to;
		em.add(email);
		return b;
	}
	public void sendEmail()
	{ 
		to = (String) em.get(1);
		String message = "id: =";
		String subject = "address: ";
		
		String from = "puru200434@gmail.com";
		System.out.println("Preparing to send message");
		
		String host = "smtp.gmail.com";
		Properties p = System.getProperties();
		System.out.println("Properties = "+p);
		
		p.put("mail.smtp.host", host);
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.auth", "true");
		Session s = Session.getInstance(p, new Authenticator()
				{
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						return new PasswordAuthentication("puru200434@gmail.com","rnnzvljmomficlxb");
					}
			
				});
		MimeMessage m = new MimeMessage(s);
		try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
			Transport.send(m);
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Completed");
		}
	}
}