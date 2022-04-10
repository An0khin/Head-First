package com.home;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChat2{
	BufferedReader br;
	PrintWriter pw;
	JTextArea mes;
	JTextField text;
	Socket sc;

	public static void main(String[] args){
		SimpleChat2 sc = new SimpleChat2();
		sc.go();
	}
	public void go(){
		JFrame frame = new JFrame();

		JPanel panel = new JPanel();
		
		mes = new JTextArea(15,50);
		mes.setLineWrap(true);
		mes.setWrapStyleWord(true);
		mes.setEditable(false);
		JScrollPane scrol = new JScrollPane(mes);
		scrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrol.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		text = new JTextField(30);

		JButton send = new JButton("Send");
		send.addActionListener(new SendButton());

		panel.add(scrol);
		panel.add(text);
		panel.add(send);

		network();

		Thread reader = new Thread(new IncomReader());
		reader.start();

		frame.getContentPane().add(panel);
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void network(){
		try{
			sc = new Socket("192.168.0.106",4242);
			br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			pw = new PrintWriter(sc.getOutputStream());
		} catch (Exception e) {}
	}
	class SendButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			try{
				pw.println(text.getText());
				pw.flush();
			} catch (Exception e) {}

			text.setText("");
		}
	}
	class IncomReader implements Runnable{
		public void run(){
			String message;
			try{
				while((message = br.readLine()) != null){
					System.out.println(message);
					mes.append(message + "\n");
				}
			} catch (Exception e) {}
		}
	}
}