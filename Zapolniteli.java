import java.util.*;

public class Zapolniteli
{
	public static void main(String[] args) {
		Zapolniteli z = new Zapolniteli();
		z.go();
	}
	public void go()
	{
		ArrayList<Animal> animals = new ArrayList<Animal>();
		animals.add(new Cat());
		animals.add(new Dog());
		animals.add(new Dog());
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog());
		dogs.add(new Dog());

		start(animals);
		start(dogs);


	}
	public void start(ArrayList<? extends Animal> animals)
	{
		for(Animal a : animals)
		{
			a.eat();
		}
	}
	// public <T extends Animal> void start(ArrayList<T> animals)
	// {
	// 	for(Animal a : animals)
	// 	{
	// 		a.eat();
	// 	}
	// 	animals.add(new Cat());
	// }

	// Это 2 одинаковые по результату синтаксиса. Благодаря ним в качестве аргумента можно использовать обобщенные ArrayList'ы с дочерними элементами от Animal. 
	// При этом будет возможно только считывание информации из списков.
}
class Animal{	
	public void eat()
	{
		System.out.println("Animal is eating");
	}
}
class Dog extends Animal
{
	
}
class Cat extends Animal
{
	
}