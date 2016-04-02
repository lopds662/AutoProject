package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDriver {

	private String[] dataArr;
	private String patternSize = "(\\bsize\\b)(\\s+)(-?\\d{3,})(\\s+)(-?\\d{3,})(\\s+)([\\[])";// (.*)";//([\\]])";
	private String patternMove = "(\\bmove\\b)(\\s+)(\\w{1,8})(\\s+)(\\w+)(\\s+)(\\w+)(\\s+)([;])";
	private String patternRect = "(\\brect\\b)(\\s+)(\\w{1,8})(\\s+)(-?\\d+)(\\s+)(-?\\d+)(\\s+)(\\d+)"
			+ "(\\s+)(\\d+)(\\s+)(\\w{1,8})(\\s+)([;])";
	private String patternLoop = "(\\bfor\\b)(\\s+)(\\w{1,8})(\\s+)([=])(\\s+)(-?\\d+)(\\s+)(\\bto\\b)(\\s+)"
			+ "(-?\\d+)(\\s+)(\\bby\\b)(\\s+)(\\d+)(\\s+)(\\bdo\\b)";// (\\s+)";//(.*)(\\bend\\b)(\\s+)([;])";
	private String patternLine = "(\\bline\\b)(\\s+)(\\w{1,8})(\\s+)(\\w+)(\\s+)(\\w+)(\\s+)(\\w+)"
			+ "(\\s+)(\\w+)(\\s+)(\\w{1,8})(\\s+)([;])";
	private String patternAssign = "(\\w{1,8})(\\s+)([=])(\\s+)(\\w+)(\\s+)([;])";
	// private String[] patternList = new String[6];
	private String pattern;
	private ArrayList<String> outputTable = new ArrayList<String>();
	private ArrayList<String> colorMap = new ArrayList<String>();
	private String out = "";
	private HashMap typeMap = new HashMap<String, String>();
	public HashMap getTypeMap() {
		return typeMap;
	}

	private String symbolDataToConcat = "";
	private String fullData = "";
	public CustomDriver(){
		typeMap.put("size", "s");
		typeMap.put("move", "m");
		typeMap.put("line", "l");
		typeMap.put("rect", "r");
		typeMap.put("for", "f");
		typeMap.put("to", "t");
		typeMap.put("by", "b");
		typeMap.put("do", "d");
		typeMap.put("end", "e");
		typeMap.put("=", "=");
		typeMap.put(";", ";");
		typeMap.put("[", "[");
		typeMap.put("]", "]");
		colorMap.add("red");
		colorMap.add("blue");
		colorMap.add("yellow");
		colorMap.add("green");
		colorMap.add("orange");
		colorMap.add("black");
		colorMap.add("pink");
	}
	public CustomDriver(String[] dataArr) {
		this.dataArr = dataArr;
		typeMap.put("size", "s");
		typeMap.put("move", "m");
		typeMap.put("line", "l");
		typeMap.put("rect", "r");
		typeMap.put("for", "f");
		typeMap.put("to", "t");
		typeMap.put("by", "b");
		typeMap.put("do", "d");
		typeMap.put("end", "e");
		typeMap.put("=", "=");
		typeMap.put(";", ";");
		typeMap.put("[", "[");
		typeMap.put("]", "]");
		colorMap.add("red");
		colorMap.add("blue");
		colorMap.add("yellow");
		colorMap.add("green");
		colorMap.add("orange");
		colorMap.add("black");
		colorMap.add("pink");
		pattern = patternSize + "|" + patternMove + "|" + patternRect + "|" + patternLoop + "|" + patternLine + "|"
				+ patternAssign + "|([\\]])|((\\bend\\b)(\\s+)([;]))";

	}

	public String[] initOutputData() {
		for (int i = 0; i < outputTable.size(); i++) {
			String temp = outputTable.get(i);
			String token = temp;
			String typee = "v";
			String str_v = token;
			String int_v = "0";
			if (typeMap.containsKey(token)) {
				typee = (String) typeMap.get(token);
				str_v = " ";
				int_v = " ";
			}
			if (colorMap.contains(token)){
				typee = "c";
			}
			
			if (isInteger(token)) {
				int_v = "" + token;
				typee = "i";
			}
			fullData += token + "+" + typee + "+" + str_v + "+" + int_v + "\n";
		}
		return fullData.split("\n");
	}

	public void matchPattern() {
		fullData = "";
		Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
		for (int i = 0; i < this.dataArr.length; i++) {
			Matcher m = r.matcher(this.dataArr[i].toLowerCase());
			if (m.find()) {
				for (int j = 1; j <= m.groupCount(); j++) {
					String temp = m.group(j);
					if (temp != null) {
						if (!m.group(j).matches("\\s+")) {
							System.out.println("Found value at [i=" + i + " j=" + j + "] is " + temp);
							if (!temp.equals("end ;")){
								outputTable.add(temp);
							}
						}
					}
				}
			}
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
