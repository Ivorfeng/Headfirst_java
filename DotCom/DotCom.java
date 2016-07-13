import java.util.*;

public class DotCom {

	private ArrayList<String> locationCells;
	private String name;
	
	public void setLocationCells(ArrayList<String> locs) {
		locationCells = locs;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public String checkYourself(String stringGuess) {
		
		String result = "miss";
		int index = locationCells.indexOf(stringGuess);
		
		if (index >= 0){
			locationCells.remove(index);
			
			if (locationCells.isEmpty()){
				result = "kill";
			}else {
				result = "hit";
			}
		}
		
		System.out.println(result);
		
		return result;
	}

}
