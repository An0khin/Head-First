public class Synchronize implements Runnable{
	int balance;
	public static void main(String[] args){
		Synchronize s = new Synchronize();
		Thread a = new Thread(s);
		Thread b = new Thread(s);
		a.start();
		b.start();
	}
	public void run(){
		for (int i = 0; i < 25; i++){
			increment();
			System.out.println(balance);
		}
	}
	public synchronized void increment(){
		int i = balance;
		balance = i + 1;
	}
}