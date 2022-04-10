import java.net.*;
import java.io.*;

public class Chat{
	public static void main(String[] args){
		Chat c = new Chat();
		c.go();	
	}
	public void go(){
		try{
			// ServerSocket servSock = new ServerSocket(4242);
			// while(true) {
			// 	Socket sock = servSock.accept();
			// 	// Socket sock = new Socket("192.168.0.10",5000);
			// 	PrintWriter wr = new PrintWriter(sock.getOutputStream());
			// 	wr.println("HELLOOOOO");
			// 	wr.close();
			// 	System.out.println("Ok");
			// 	// BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			// 	// System.out.println(br.readLine());
			// }	

			Socket sock = new Socket("192.168.0.10",4242);

			BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println(br.readLine());
			br.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}