package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomScanner {
	
	private String data, outData;
	private Pattern patternComment;
	
	public CustomScanner(String data){
		this.data = data;
		this.outData = data.replaceAll("(?m)%.*$","");
		System.out.println(outData);
		initArr(outData);
	}
	public void initArr(String data){
		String[] dataArr = data.split("\n");
		for (int i =0; i<dataArr.length;i++){
			System.out.println(">>>>> "+dataArr[i]);
		}
		CustomDriver driver = new CustomDriver(dataArr);
	}
	
	public String toString(){
		return outData;
	}
}
