import java.util.*;
import java.io.*;

public class Pets{
	public static void main(String[] args){
		Animal a1 = new Dog();
		a1.setName();
		a1.sound();
		a1.sound("Hello");
		a1.sound(53);

		Dog[] ar = new Dog[5];
		for (int j = 0; j < 5; j++) {
			ar[j] = new Dog();
		}
		ar[3].accompany();
		ar[3].setName();
		System.out.println(Animal.count);
		System.out.println(Animal.COST);
	}
}

abstract class Animal{
	static final int COST; //final - модификатор, с которым переменную не изменить после инициализации
	static{ //
		COST = 54;
	}
	int hunger = 0;
	int sleep = 0;
	int hapiness = 0;
	String name = "Klein";
	static int count; //static переменная ни отчего не зависит, т.е. она одна на все экземпляры

	Animal(){
		count++;
	}

	abstract void sound();
	abstract void sound(String s);
	abstract void sound(int i);
	
	void setName() {
		System.out.print("This pet's name will be ");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			name = br.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}

class Dog extends Animal implements Pet, Accompanier{
	void sound(){
		System.out.println(name + " says: WUF");
	}
	void sound(String s){
		System.out.println("S: " + s);
	}
	void sound(int i){
		System.out.println("I: " + i);
	}
	void setName() {
		super.setName(); //Запускает родительский сэтНэйм и после выполняет следующий код(не переопределяет):
		name += " Fick";
	}
	public void play(){
		hapiness += 10;
	}
	public void feed(){
		hapiness += 1;
		hunger -= 30;
	}
	public void accompany(){
		System.out.println(name + " is accompaning you");
	}
}

interface Pet{
	void play();
	void feed();
}
interface Accompanier{
	void accompany();
}