package com.home;

import java.net.*;
import java.io.*;
import java.util.*;

public class TestChatServ 
{
	ArrayList users;

	public static void main(String[] args) 
	{
		TestChatServ cs = new TestChatServ();
		cs.go();
	}
	public void go() 
	{
		users = new ArrayList();
		try 
		{
			ServerSocket ss = new ServerSocket(3400);

			while(true) 
			{
				Socket user = ss.accept();

				System.out.println("Success");

				PrintWriter pw = new PrintWriter(user.getOutputStream());
				users.add(pw);

				Thread thr = new Thread((Runnable) new Client(user));
				thr.start();
			}
		} 
		catch (Exception e) 
		{
		
		}
	}
	class Client implements Runnable 
	{
		BufferedReader br;
		public Client(Socket user) 
		{
			try 
			{
				br = new BufferedReader(new InputStreamReader(user.getInputStream()));
			} 
			catch (Exception e) 
			{

			}
		}
		public void run() 
		{
			String mes;
			try 
			{
				tellEveryone("hello");
				while((mes = br.readLine()) != null) 
				{
					tellEveryone(mes);
				}
			} 
			catch (Exception e) 
			{

			}
		}
		public void tellEveryone(String mes) 
		{

			Iterator it = users.iterator();
			while(it.hasNext()) 
			{
				try 
				{
				
				PrintWriter pw = (PrintWriter) it.next();
				pw.println(mes);
				pw.flush();
				
				}
				catch (Exception e) 
				{

				}
			} 
			
		}
	}
}