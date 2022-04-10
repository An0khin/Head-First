package com.home;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestChatClient
{
	JTextArea text;
	PrintWriter pw;
	Socket sock;
	BufferedReader br;

	public static void main(String[] args) 
	{
		TestChatClient tcc = new TestChatClient();
		tcc.go();
	}

	public void go() 
	{
		JFrame frame = new JFrame();

		JPanel panel = new JPanel();

		text = new JTextArea(10,20);
		text.setEditable(false);
		JScrollPane scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		panel.add(BorderLayout.CENTER, scroll);

		setUpConnect();
		Thread thr = new Thread((Runnable) new Reader());
		thr.start();

		frame.getContentPane().add(panel);

		frame.setSize(300,300);
		frame.setVisible(true);
	}
	public void setUpConnect()
	{
		try
		{
			sock = new Socket("192.168.0.106",3400);
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		}
		catch(Exception e)
		{

		}
		
	}
	class Reader implements Runnable
	{	
		public void run() 
		{
			String mes;
			try
			{
				while((mes = br.readLine()) != null) 
				{
					text.append(mes + "\n");
				}
			}
			catch(Exception e)
			{

			}
		}
	}
}