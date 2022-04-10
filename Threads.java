import java.io.*;

public class Threads{
	public static void main(String[] args) {
		Threads t = new Threads();
		t.go();
	}
	public void go(){
		Runnable wr = new Write();
		Thread thr = new Thread(wr);
		thr.start();

		for (int i = 0; i<1000;i++){
			System.out.println(i);
			try{
				Thread.sleep(30);
			} catch (Exception e) {}
			
		}
	}
	class Write implements Runnable{
		public void run(){
			try{
				String word = "";
				while(!word.equals("exit")){
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					word = br.readLine();
				}
			} catch (Exception e) {}
		}
	}
}