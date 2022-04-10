import java.util.*;
import java.io.*;

class DotComDrive {
	public static void main(String[] args) {
		GameHelper help = new GameHelper();

		List<DotCom> Dots = new ArrayList<DotCom>();

		DotCom one = new DotCom();
		DotCom two = new DotCom();
		DotCom three = new DotCom();

		Dots.add(one);
		Dots.add(two);
		Dots.add(three);

		for (DotCom Dot : Dots) {
			Dot.setLocation(help.placeDot(3));
		}

		String result = "miss";
		int numbGuesses = 0;

		while(!Dots.isEmpty()) {
			String guess = help.getGuess();
			for (DotCom Dot : Dots) {
				result = Dot.checkHit(guess);
				if (result.equals("hit")) {
					break;
				} else if (result.equals("kill")) {
					Dots.remove(Dot);
					break;
				}
			}
			numbGuesses++;
			System.out.println(result);
		}

		System.out.println("You've used "+numbGuesses+" attempts");
	}
}

class DotCom {
	private List<String> location;
	private int hits = 0;

	public void setLocation(List<String> cells) {
		location = cells;
		System.out.println(location);
	}

	public String checkHit(String guess) {
		String answer = "miss";
		if (location.contains(guess)) {
			hits++;
			answer = "hit";
			location.remove(guess);
		}
		if (location.size() <= 0) {
			answer = "kill";
		}
		return answer;
	}
}

class GameHelper {
	String alph = "abcdefg";
	int gridSize = 49;
	int[] grid = new int[gridSize];
	int gridLength = 7;

	public String getGuess(){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			return in.readLine();
		} catch(IOException e) {
			System.out.println(e);
		}	
		return null;
	}
	 public List<String> placeDot(int sizeDot) {
	 	List<String> finLoc = new ArrayList<String>();
	 	int[] testLoc = new int[sizeDot];
	 	int ink = 1;
	 	int x;
	 	
	 	boolean success = false;

	 	if (Math.random() < 0.5) {
	 		ink = 7;
	 	}

	 	while (!success) {
	 		int point = (int) (Math.random() * gridSize);
		 	x = 0;
		 	success = true;

		 	while (success & x < sizeDot) {
		 		if (grid[point] == 0) {

		 			testLoc[x++] = point;
		 			point += ink;

		 			if (point > gridSize) {
		 				success = false;
		 			}

		 			if (point % gridLength == 0) {
		 				success = false;
		 			}


		 		} else {
		 			success = false;
		 		}
		 	}
	 	}
	 	x = 0;
		int row;
		int column;
		String temp;

	 	System.out.println("Test: " +Arrays.toString(testLoc));

	 	while(x < sizeDot) {
	 		grid[testLoc[x]] = 1;

	 		row = (int) (testLoc[x] / gridLength);
	 		column = testLoc[x] % gridLength;

	 		temp = String.valueOf(alph.charAt(column));
	 		finLoc.add(temp.concat(Integer.toString(row)));
	 		x++;
	 	}
	 	return finLoc;
	 }
}