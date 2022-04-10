import java.util.*;
import java.io.*;

class SimpleDotComDrive {
	public static void main(String[] args) {
		SimpleDotCom boat = new SimpleDotCom();
		GameHelper help = new GameHelper();

		int randNumb = (int) (Math.random() * 5);
		System.out.println(randNumb);
		List<Integer> loc = new ArrayList<Integer>();
		loc.add(randNumb);
		loc.add(++randNumb);
		loc.add(++randNumb);
		boat.setLocation(loc);

		String result;
		int numbGuesses = 0;

		while(true) {
			result = boat.checkHit(help.getGuess());
			numbGuesses++;
			System.out.println(result);
			if (result.equals("kill")) {
				break;
			}
		}

		System.out.println("You have used "+numbGuesses+" attempts");
	}
}

class SimpleDotCom {
	private List<Integer> location;
	private int hits = 0;

	public void setLocation(List<Integer> cells) {
		location = cells;
	}

	public String checkHit(String guess) {
		String answer = "miss";
		if (location.contains(Integer.parseInt(guess))) {
			hits++;
			answer = "hit";
			location.remove(location.indexOf(Integer.parseInt(guess)));
		}
		if (location.size() <= 0) {
			answer = "kill";
		}
		return answer;
	}
}

class GameHelper {
	public String getGuess(){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			return in.readLine();
		} catch(IOException e) {
			System.out.println(e);
		}	
		return null;
	}
}