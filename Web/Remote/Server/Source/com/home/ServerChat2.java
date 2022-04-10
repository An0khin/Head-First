package com.home;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServerChat2{
	ArrayList Streams;
	
	public static void main(String[] args){
		ServerChat2 sc2 = new ServerChat2();
		sc2.go();
	}

	public void go(){
		Streams = new ArrayList();
		try{
			ServerSocket ss = new ServerSocket(4242);

			while(true){
				Socket sock = ss.accept();
				PrintWriter pw = new PrintWriter(sock.getOutputStream());
				Streams.add(pw);

				Thread thr = new Thread((Runnable) new ClientHandler(sock));
				thr.start();
			}
		} catch (Exception e) {}
	}
	
	class ClientHandler implements Runnable{
		BufferedReader br;
		
		public ClientHandler(Socket sock) {
			try{
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			} catch(Exception e) {}
		}

		public void run(){
			String message;
			try{
				while ((message = br.readLine()) != null) {
					System.out.println(message);
					tellEveryone(message);
				}
			} catch (Exception e) {}
		}
		public void tellEveryone(String message) {
			Iterator it = Streams.iterator();
			while(it.hasNext()){
				try{
					PrintWriter pw = (PrintWriter) it.next();
					pw.println(message);
					pw.flush();
				} catch (Exception e) {}
			}
		}
	}
}