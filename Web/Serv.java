import java.net.*;
import java.io.*;
import java.util.*;

public class Serv
{
	ArrayList users;
	public static void main(String[] args)
	{
		Serv s = new Serv();
		s.go();
	}
	public void go()
	{
		users = new ArrayList();
		try
		{
			ServerSocket ss = new ServerSocket(3434);

			while(true)
			{
				Socket sock = ss.accept();
				System.out.println("Success");

				PrintWriter pw = new PrintWriter(sock.getOutputStream());
				users.add(pw);

				Thread thr = new Thread(new Client(sock));
				thr.start();
			}
		}
		catch(Exception e)
		{

		}
	}
	class Client implements Runnable
	{
		BufferedReader br;
		public Client(Socket sock)
		{
			try
			{
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			}
			catch(Exception e)
			{

			}
		}
		public void run()
		{
			String mes;
			try
			{
				while((mes = br.readLine()) != null)
				{
					tell(mes);
				}
			}
			catch(Exception e)
			{

			}
		}
		public void tell(String mes)
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
				catch(Exception e)
				{

				}
			}
		}
	}
}