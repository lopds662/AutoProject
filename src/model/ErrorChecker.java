package model;

import java.util.ArrayList;

public class ErrorChecker {
	
	String testData, errorPharse = "";
	String[] testSet;
	int counter = 0, line;
	ArrayList<Character> specialChar = new ArrayList<>();
	ArrayList<String> typeMap = new ArrayList<String>();

	public boolean MainChecker(String inn, int n){
		testData = inn;
		line = n + 1;
		testSet = testData.trim().split("\\s+");
		counter = 0;
		switch (testSet[counter].trim()){
		case "size" :
			System.out.println("size");
			return true;
		case "line" :
			System.out.println("line");
			return true;
		case "end" :
			System.out.println("end");
			return true;
		case "move" :
			System.out.println("move");
			return true;
		case "rect" :
			System.out.println("rect");
			return true;
		case "for" :
			System.out.println("for");
			return true;
		case "%" :
			System.out.println("%");
			return true;
		default :
            System.out.println("Assign");
            return true;
		}
	}
	public void checkSize(){
		counter += 1;
		String t = testSet[counter].trim();
	}
	
	public void isSizeInteger(String test) {
		if (isInteger(test)) {
			int temp = Math.abs(Integer.parseInt(test));
			if (temp >= 200) {
				counter += 1;
			} else {
				errorPharse += "In line " + line + " : " + testSet[counter] + " is less than 200.\n";
			}
		} else {
			errorPharse += "In line " + line + " : " + testSet[counter] + " is an Integer.\n";
		}
	}
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

}
