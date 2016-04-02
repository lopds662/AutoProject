package model;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorChecking {

	String testData, errorPharse = "";
	String[] testSet;
	int counter = 0, line;
	ArrayList<Character> specialChar = new ArrayList<>();
	ArrayList<String> typeMap = new ArrayList<String>();

	public boolean MainCheck(String inn, int n) {
		typeMap.add("size");
		typeMap.add("move");
		typeMap.add("line");
		typeMap.add("rect");
		typeMap.add("for");
		typeMap.add("to");
		typeMap.add("by");
		typeMap.add("do");
		typeMap.add("end");
		typeMap.add("=");
		typeMap.add(";");
		typeMap.add("[");
		typeMap.add("]");
		specialChar.add('@');
		specialChar.add('=');
		specialChar.add('+');
		specialChar.add('-');
		specialChar.add('!');
		specialChar.add('*');
		specialChar.add('(');
		specialChar.add(')');
		specialChar.add('&');
		specialChar.add('^');
		specialChar.add('%');
		specialChar.add('$');
		specialChar.add(']');
		specialChar.add('[');
		specialChar.add(';');
		specialChar.add('/');
		specialChar.add('#');
		testData = inn.trim();
		line = n + 1;
		testSet = testData.trim().split("\\s+");
		counter = 0;
		if (testSet[counter].trim().equals("%")){
			return true;
		}
		if (testSet[counter].trim() == ""){
			return true;
		}
		if (testSet[counter].trim() == null){
			return true;
		}
		if (testSet[counter].trim().equals("end")){
			return true;
		}
		if (testSet[counter].trim().equals("]")){
			return true;
		}
		if (isSizeCommand()) {
			return true;
		}
//		else if (isAssignCommand()) {
//			return true;
//		} 
		else if (isLineCommand()){
			return true;
		} 
		else {
			return true;
		}
	}	//line v a b c d color ;
	
	public boolean isLineCommand(){
		if (testSet[counter].trim().equals("line")){
			System.out.println(">>>>>>>>>>>>>>>>>>>>SSS >>>>>>> "+testData.trim());
			counter += 1;
			if (isVar(testSet[counter].trim())){
				counter += 1;
				if (isSpecialChar(testSet[counter].trim())){
					counter += 1;
					if (isSpecialChar(testSet[counter].trim())){
						counter += 1;
						if (isSpecialChar(testSet[counter].trim())){
							counter += 1;
							if (isSpecialChar(testSet[counter].trim())){
								counter += 1;
								if (isVar(testSet[counter].trim())){
									counter += 1;
									if (counter < testData.length()) {
										if (testSet[counter].trim().equals(";")) {
											return true;
										} else {
											errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
											return false;
										}
									} else {
										errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
										return false;
									}
								}else {
									errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
									return false;
								}
							}else {
								errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
								return false;
							}
						}else {
							errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
							return false;
						}
					}else {
						errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
						return false;
					}
				}else {
					errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
					return false;
				}
			}else {
				errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
				return false;
			}
		}
		counter = 0;
		return false;
	}

	public boolean isAssignCommand() {
		if (isVar(testSet[counter].trim())) {
			System.out.println(testData +"AAAAAAAAA  "+counter + " SAAAAAAAAAAAAAAAAAAA");
			if (testSet[counter].trim().equals("=")) {
				counter += 1;
				if (!isSpecialChar(testSet[counter].trim())) {
					counter += 1;
					if (counter < testData.length()) {
						if (testSet[counter].trim().equals(";")) {
							return true;
						} else {
							errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
							return false;
						}
					} else {
						errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
						return false;
					}
				} else {
					errorPharse += "In line " + line + " : " + testSet[counter]
							+ " is invalid token. Can not use special character as variable\n";
					counter = 0;
					return false;
				}
			} else {
				errorPharse += "In line " + line + " : " + testSet[counter] + " is invalid token.\n";
				return false;
			}
		}
		counter = 0;
		return false;
	}

	public boolean isVar(String test) {
		if (!isInteger(test)) {
			if (test.length() <= 8) {
				counter += 1;
				return true;
			} else {
				errorPharse += "In line " + line + " : " + testSet[counter] + " variable is too long.\n";
				counter = 0;
				return false;
			}
		} else {
			errorPharse += "In line " + line + " : " + testSet[counter] + " is an Integer.\n";
			counter = 0;
			return false;
		}
	}

	public boolean isSpecialChar(String test) {
		String temp = test;
		for (int i = 0; i < temp.length(); i++) {
			if (specialChar.contains(temp.charAt(i))) {
				counter = 0;
				return false;
			}
		}
		return true;
	}

	public boolean isSizeCommand() {
		if (isSize(testSet[counter].trim())) {
			if (isSizeInteger(testSet[counter].trim())) {
				if (isSizeInteger(testSet[counter].trim())) {
					if (testSet[counter].equals("[")) {
						return true;
					} else {
						errorPharse += "Invalid data: " + testSet[counter].trim() + " at line: " + line + "\n\t\'"
								+ testSet[counter].trim() + "\'" + "  ERROR Token.\n";
						counter = 0;
						return false;
					}
				}
			}
		}
		counter = 0;
		return false;
	}

	public boolean isSize(String test) {
		if (test.trim().equals("size")) {
			counter += 1;
			return true;
		}
		counter = 0;
		return false;
	}

	public boolean isSizeInteger(String test) {
		if (isInteger(test)) {
			int temp = Math.abs(Integer.parseInt(test));
			if (temp >= 200) {
				counter += 1;
				return true;
			} else {
				errorPharse += "Invalid data: " + testSet[counter] + " at line: " + line + "\n\t\'" + testSet[counter]
						+ "\'" + " is less than 200.\n";
				counter = 0;
				return false;
			}
		} else {
			errorPharse += "Invalid data: " + testSet[counter] + " at line: " + line + "\n\t\'" + testSet[counter]
					+ "\'" + " is not an Integer.\n";
			counter = 0;
			return false;
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
