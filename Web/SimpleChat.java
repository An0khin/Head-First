import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChat{
	JTextField text;
	Socket sock;
	PrintWriter wr;
	public static void main(String[] args){
		SimpleChat sc = new SimpleChat();	
		sc.go();
	}
	public void go(){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		JButton send = new JButton("Send");
		send.addActionListener(new SendButton());

		text = new JTextField(20);
		text.addActionListener(new TextField());

		panel.add(text);
		panel.add(send);

		network();

		frame.getContentPane().add(BorderLayout.CENTER, panel);

		frame.setSize(400,400);
		frame.setVisible(true);
	}
	private void network(){
		try{
			sock = new Socket("192.168.0.10",5000);
			wr = new PrintWriter(sock.getOutputStream());
		} catch (Exception e) {}
	}
	class SendButton implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			try{
				wr.println(text.getText());
				wr.flush();
			} catch (Exception e) {}

			text.setText("");
		}
	}
	class TextField implements ActionListener{
		public void actionPerformed(ActionEvent ae){

		}
	}
}