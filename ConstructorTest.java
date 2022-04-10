public class ConstructorTest{
	public static void main(String[] args){
		Some obj = new Some();
	}
}

class Some extends Thing{
	public Some(String a) {
		super(); //Запускает конструктор родителя с подоходящими по типу параметрами
		System.out.println(a); 
	}
	public Some() {
		this("Go away!"); //Запускает конструктор этого класса с подоходящими по типу параметрами
	}
}
class Thing{
	public Thing(){
		System.out.println("I'm alive!!!");
	}
}