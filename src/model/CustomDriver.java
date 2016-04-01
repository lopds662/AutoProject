package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDriver {

	private String[] dataArr;
	private String patternSize = "(\\bsize\\b)(\\s+)((([-])(\\d{3,}))|(\\d{3,}))(\\s+)((([-])(\\d{3,}))|(\\d{3,}))(\\s+)([\\[])";//(.*)";//([\\]])";
	private String patternMove = "(\\bmove\\b)(\\s+)(\\w{1,8})(\\s+)((([-])(\\d+))|(\\d+))(\\s+)((([-])(\\d+))|(\\d+))(\\s+)([;])";
	private String patternRect = "(\\brect\\b)(\\s+)(\\w{1,8})(\\s+)((([-])(\\d+))|(\\d+))(\\s+)((([-])(\\d+))|(\\d+))(\\s+)(\\d+)"
			+ "(\\s+)(\\d+)(\\s+)(\\w{1,8})(\\s+)([;])";
	private String patternLoop = "(\\bfor\\b)(\\s+)(\\w{1,8})(\\s+)([=])(\\s+)((([-])(\\d+))|(\\d+))(\\s+)(\\bto\\b)(\\s+)"
			+ "((([-])(\\d+))|(\\d+))(\\s+)(\\bby\\b)(\\s+)(\\d+)(\\s+)(\\bdo\\b)";//(\\s+)";//(.*)(\\bend\\b)(\\s+)([;])";
	private String patternLine = "(\\bline\\b)(\\s+)(\\w{1,8})(\\s+)((([-])(\\d+))|(\\d+))(\\s+)((([-])(\\d+))|(\\d+))(\\s+)(\\d+)"
			+ "(\\s+)(\\d+)(\\s+)(\\w{1,8})(\\s+)([;])";
	private String patternAssign = "(\\w{1,8})(\\s+)([=])(\\s+)(\\w+)(\\s+)([;])";
	private String pattern;
	
	private String[] outputTable;
	private String out = "";
	
	public CustomDriver(String[] dataArr){
		this.dataArr = dataArr;
		pattern = patternSize + "|" 
				+ patternMove + "|"
				+ patternRect + "|"
				+ patternLoop + "|"
				+ patternLine + "|"
				+ patternAssign + "|[\\]]|((\\bend\\b)(\\s+)([;]))";
		matchPattern();
		
	}
	
	public void matchPattern(){
		Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
		for (int i = 0; i<this.dataArr.length; i++){
			Matcher m = r.matcher(this.dataArr[i]);
			if (m.find()) {
				System.out.println("Group Count = " + m.groupCount());
				for (int j = 1; j <= m.groupCount(); j++){
					String temp = m.group(j);
					if (temp != null) {
						if (!m.group(j).matches("\\s+")){
							System.out.println("Found value at [i="+i+" j=" + j + "] is " + temp);
						}
					}
				}
			}
		}
	}
}
