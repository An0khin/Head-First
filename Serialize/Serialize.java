import java.io.*;

public class Serialize{
	Char c = null;
	public static void main(String[] args){
		Serialize s = new Serialize();
		s.go(s);
	}
	public void go(Serialize s){
		String l = "";
		while (!l.equals("exit")){
			l = ask();
			if (l.equals("1")){
				c.getHealth();
			} else if(l.equals("2")){
				c.setHealth(Integer.parseInt(ask()));
			} else if(l.equals("save")){
				s.save();
			} else if(l.equals("load")){
				s.load();
			} else {
				c = new Char();
			}
		}
	}
	public String ask(){
		String res = "";
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			res = br.readLine();
		}catch(Exception ex){}
		return res;
	}

	public void save(){
		try{
			FileOutputStream fs = new FileOutputStream("file.sez");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(c);
			os.close();
		} catch(Exception ex) {}
	}
	public void load(){
		try{
			FileInputStream fs = new FileInputStream("file.sez");
			ObjectInputStream os = new ObjectInputStream(fs);
			c = (Char) os.readObject();
			os.close();
		}catch(Exception ex){}
	}
}
class Char implements Serializable{
	int health = 100;
	String weapon = "Sword";	
	transient int id = 123124; //transient - не сериализуется

	public void getHealth(){
		System.out.println(health);
	}
	public void setHealth(int h){
		health = h;
	}
}