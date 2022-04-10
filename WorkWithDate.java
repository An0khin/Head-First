import java.util.*;

public class WorkWithDate {
	public static void main(String[] args) {
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
		System.out.println("It's " + d);
		System.out.println(String.format("It's %tI",d));
	}
}