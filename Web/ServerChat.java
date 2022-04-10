import java.net.*;
import java.io.*;

public class ServerChat{
	public static void main(String[] args){
		ServerChat sc = new ServerChat();
		sc.go();
	}

	public void go(){
		try{
			ServerSocket servSock = new ServerSocket(4242);

			while(true){
				Socket sock = servSock.accept();

				PrintWriter pr = new PrintWriter(sock.getOutputStream());
				pr.println("Hi");
				pr.close();
				System.out.println("ok");
			}
			
			// Socket sock = new Socket("192.168.0.17",4242);
			// // PrintWriter wr = new PrintWriter(sock.getOutputStream());
			// // wr.println("HELLOOOOO");
			// BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			// System.out.println(br.readLine());
			// br.close();

		} catch (IOException e) {e.printStackTrace();}
	}

}