package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckError {
	String testData, errorPharse = "";
	String[] testSet;
	int counter = 0, line;
	ArrayList<Character> specialChar = new ArrayList<>();
	ArrayList<String> typeMap = new ArrayList<String>();
	
	List<Character> special = new ArrayList<>(Arrays.asList('*', '!', '@', '#','&','$','!','-'));
	
	public void MainChecker(String inn, int n){
		testData = inn;
		line = n + 1;
		testSet = testData.trim().split("\\s+");
		counter = 0;
		for (int i = 0; i < testSet.length; i++){
			String t = testSet[i].trim();
			if (t.length() > 8){
				errorPharse += "In line " + line + " : " + t + " variable is too long.\n";
			}
			if (!t.equals(";")){
				for (int j = 0; j<t.length(); j++){
					if (t.charAt(j) == ';'){
						errorPharse += "In line " + line + " : " + t + ""
								+ "There is no separator between "+ t +"and ;"
										+ " and treat as a token which is invalid.\n";
					}
					if (special.contains(t.charAt(j))){
						errorPharse += "In line " + line + " : " +t  + " is invalid token.\n";
					}
				}
			}
		}	
	}
}
