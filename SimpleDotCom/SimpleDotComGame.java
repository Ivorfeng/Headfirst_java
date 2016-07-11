import java.util.ArrayList;

public class SimpleDotComGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numOfGuesses = 0;
		GameHelper helper = new GameHelper();
		
		DotCom theDotCom = new DotCom();
		//SimpleDotCom theDotCom = new SimpleDotCom();
		int randomNum =(int)(Math.random() * 5);
		ArrayList<String> locations = new ArrayList<String>();
		locations.add(Integer.toString(randomNum));
		locations.add(Integer.toString(randomNum+1));
		locations.add(Integer.toString(randomNum+2));
		//int[] locations = {randomNum, randomNum+1, randomNum+2};
		theDotCom.setLocationCells(locations);
		boolean isAlive = true;
		
		while(isAlive == true) {
			String guess = helper.getUserInput("enter a number");
			String result = theDotCom.checkYourself(guess);
			numOfGuesses++;
			if(result.equals("kill")) {
				isAlive = false;
				System.out.println("You took " + numOfGuesses + " guesses");
			}
		}
	}

}
