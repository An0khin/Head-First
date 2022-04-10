import java.net.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Client
{
	BufferedReader br;
	PrintWriter pw;
	Socket sock;
	JTextArea mess;
	JTextField myMes;
	JButton but;

	public static void main(String[] args)
	{
		Client c = new Client();
		c.go();
	}
	public void go()
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		mess = new JTextArea(15,50);
		mess.setEditable(false);
		mess.setLineWrap(true);
		mess.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(mess);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		myMes = new JTextField(15);

		but = new JButton("Send");
		but.addActionListener(new Send());

		panel.add(scroll);
		panel.add(myMes);
		panel.add(but);

		setCon();

		Thread thr = new Thread(new Reader());
		thr.start();

		frame.getContentPane().add(panel);
		frame.setSize(300,300);
		frame.setVisible(true);

	}
	public void setCon()
	{
		try
		{
			sock = new Socket("192.168.0.10", 3434);
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(sock.getOutputStream());
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
					mess.append(mes + "\n");
				}
			}
			catch(Exception e)
			{

			}
		}
	}
	class Send implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			try
			{
				pw.println(myMes.getText());
				pw.flush();
				myMes.setText("");
			}
			catch(Exception e) 
			{

			}
		}
	}
}