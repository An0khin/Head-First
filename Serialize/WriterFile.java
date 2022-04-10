import java.io.*;

public class WriterFile{
	public static void main(String[] args){
		try{
			FileWriter fw = new FileWriter("file.txt");
			fw.write(args[0]);
			fw.close();
		}catch(Exception ex){};
	}
}

